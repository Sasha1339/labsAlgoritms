package ru.mpei.IEC61850.logicalNodes.measurements;

import lombok.extern.slf4j.Slf4j;
import ru.mpei.IEC61850.datatypes.measurements.SAV;
import ru.mpei.IEC61850.datatypes.measurements.WYE;
import ru.mpei.IEC61850.logicalNodes.LN;
import ru.mpei.IEC61850.logicalNodes.hmi.other.NHMISignal;
import ru.mpei.IEC61850.logicalNodes.protocol.LSVS;
import ru.mpei.IEC61850.utils.Filter;
import ru.mpei.IEC61850.utils.FourierFilter;
import ru.mpei.IEC61850.utils.MsdFilter;

@Slf4j
public class MMXU extends LN {
    //в будущем здесь будет фильтр Фурье

    public static int bufSize = 80;

    //входы
    public SAV UaInst = new SAV(); //Inst мгновеннеы величины
    public SAV UbInst = new SAV();
    public SAV UcInst = new SAV();

    public SAV IaInst = new SAV();
    public SAV IbInst = new SAV();
    public SAV IcInst = new SAV();

    //выходы

    public WYE A = new WYE();

    //переменные

    private final Filter ia = new FourierFilter(bufSize);

    private final Filter ib = new FourierFilter(bufSize);
    private final Filter ic = new FourierFilter(bufSize);
    @Override
    public void process() {
        this.ia.process(this.IaInst, A.getPhsA().getCVal());
        this.ib.process(this.IbInst, A.getPhsB().getCVal());
        this.ic.process(this.IcInst, A.getPhsC().getCVal());
    }

    @Override
    public void build(String pref, String name, Integer id, String[] parameters) {
        this.pref = pref;
        this.clazz = name;
        this.inst = id;
    }

    @Override
    public <T extends LN> void connect(T logicNode) {
        if (logicNode instanceof LSVS lsvs){
            IaInst = lsvs.getOut().get(0);
            IbInst = lsvs.getOut().get(1);
            IcInst = lsvs.getOut().get(2);
            if (lsvs.getOut().size() > 3){
                UaInst = lsvs.getOut().get(3);
                UbInst = lsvs.getOut().get(4);
                UcInst = lsvs.getOut().get(5);
            }
        } else {
            log.error("Не правильно задан ID логического угла в конфигурации связей");
        }
    }

    @Override
    public NHMISignal getSignal(String name, String parameters) {
        if (parameters != null) {
            if (parameters.contains("U") || parameters.contains("u")) {
                if (parameters.contains("A") || parameters.contains("a"))
                    return new NHMISignal(name, UaInst.getInstMag().getF());
                else if (parameters.contains("B") || parameters.contains("b"))
                    return new NHMISignal(name, UbInst.getInstMag().getF());
                else if (parameters.contains("C") || parameters.contains("c"))
                    return new NHMISignal(name, UcInst.getInstMag().getF());
            } else if (parameters.contains("I") || parameters.contains("i")) {
                if (parameters.contains("A") || parameters.contains("a"))
                    return new NHMISignal(name, IaInst.getInstMag().getF());
                else if (parameters.contains("B") || parameters.contains("b"))
                    return new NHMISignal(name, IbInst.getInstMag().getF());
                else if (parameters.contains("C") || parameters.contains("c"))
                    return new NHMISignal(name, IcInst.getInstMag().getF());
            }
        }
    return new NHMISignal(name, IaInst.getInstMag().getF());
    }
}
