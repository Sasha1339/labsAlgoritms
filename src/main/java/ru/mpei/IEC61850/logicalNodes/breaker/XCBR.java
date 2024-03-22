package ru.mpei.IEC61850.logicalNodes.breaker;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.IEC61850.datatypes.breaker.*;
import ru.mpei.IEC61850.logicalNodes.LN;
import ru.mpei.IEC61850.logicalNodes.measurements.MMXU;

@Getter
@Setter
@Slf4j
public class XCBR extends LN {
    private SPS Loc = new SPS();
    private INS OpCnt = new INS();
    public DPC Pos = new DPC();
    private SPC BlkOpn = new SPC();
    private SPC BlkCLs = new SPC();
    private INS CBOpCap = new INS();

    //Переменные

    private CodedEnum state = Pos.getStVal().getValue();
    @Override
    public void process() {
        if (Pos.getStVal().getValue() == CodedEnum.off && state == CodedEnum.on){
            OpCnt.getStVal().setValue(OpCnt.getStVal().getValue() + 1);
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
        if (logicNode instanceof CSWI cswi){
            Pos = cswi.Pos;
        } else {
            log.error("Не правильно задан ID логического угла в конфигурации связей");
        }
    }
}
