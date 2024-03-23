package ru.mpei.IEC61850.logicalNodes.breaker;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.IEC61850.datatypes.breaker.CodedEnum;
import ru.mpei.IEC61850.datatypes.breaker.DPC;
import ru.mpei.IEC61850.datatypes.protection.ACT;
import ru.mpei.IEC61850.logicalNodes.LN;
import ru.mpei.IEC61850.logicalNodes.hmi.other.NHMISignal;
import ru.mpei.IEC61850.logicalNodes.measurements.MMXU;
import ru.mpei.IEC61850.logicalNodes.protection.PTOC;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Класс логического узла CSWI - автоматики управления выключателем
 * OpOpns - данные активации всех защит
 * OpOpn - операция отключения выключателя
 * Pos - актуальное положение выключателя
 */

@Getter
@Setter
@Slf4j
public class CSWI extends LN {

    //входы
    public List<ACT> OpOpns = new ArrayList<>();
    public ACT OpOpn = new ACT();


    //выходы
    public DPC Pos = new DPC();



    @Override
    public void process() {

        Optional<ACT> Op = OpOpns.stream()
                .filter(e -> e.getGeneral().getValue() ||
                        e.getPhsA().getValue() ||
                        e.getPhsB().getValue() ||
                        e.getPhsC().getValue())
                .findAny();
        if (Op.isPresent()){
            OpOpn.getGeneral().setValue(Op.get().getGeneral().getValue());
            OpOpn.getPhsA().setValue(Op.get().getPhsA().getValue());
            OpOpn.getPhsB().setValue(Op.get().getPhsB().getValue());
            OpOpn.getPhsC().setValue(Op.get().getPhsC().getValue());
            Pos.getStVal().setValue(OpOpn.getGeneral().getValue() ||
                    OpOpn.getPhsA().getValue() ||
                    OpOpn.getPhsB().getValue() ||
                    OpOpn.getPhsC().getValue() ? CodedEnum.off : CodedEnum.on);
        }
    }
    @Override
    public void build(String pref, String name, Integer id, String[] parameters) {
        this.pref = pref;
        this.clazz = name;
        this.inst = id;
    }

    @Override
    public <T extends LN> void connect(T logicNode) {
        if (logicNode instanceof PTOC ptoc){
            OpOpns.add(ptoc.Op);
        } else {
            log.error("Не правильно задан ID логического угла в конфигурации связей");
        }
    }

    @Override
    public NHMISignal getSignal(String name,String parameters) {
        return new NHMISignal(name, OpOpn.getPhsA());
    }
}
