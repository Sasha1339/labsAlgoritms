package ru.mpei.IEC61850.logicalNodes.protection;

import lombok.extern.slf4j.Slf4j;
import ru.mpei.IEC61850.datatypes.measurements.WYE;
import ru.mpei.IEC61850.datatypes.protection.ACD;
import ru.mpei.IEC61850.datatypes.protection.ACT;
import ru.mpei.IEC61850.datatypes.setting.ASG;
import ru.mpei.IEC61850.datatypes.setting.ING;
import ru.mpei.IEC61850.logicalNodes.LN;
import ru.mpei.IEC61850.logicalNodes.hmi.other.NHMISignal;
import ru.mpei.IEC61850.logicalNodes.measurements.MMXU;

/**
 * Класс логического узла PTOC - токовой защиты
 * dt - шаг дискретизации
 * A - действующие значения токов по фазам
 * Str - срабатывание защиты при превышении уставки
 * Op - срабатывание защиты по факту набора времени
 * Str - уставка по току
 * OpDlTmms - уставка по времени
 * cntTimeA - набор времени для фазы А по факту срабаывания Str
 * cntTimeB - набор времени для фазы B по факту срабаывания Str
 * cntTimeC - набор времени для фазы C по факту срабаывания Str
 */

@Slf4j
public class PTOC extends LN {

    //Str = start, when current more then set
    // StrVal = set ASG - max value of current
    // OpDITmms

    public static double dt = 0.250; //мсек

    //Входы

    public WYE A = new WYE();

    //Выходы

    public ACD Str = new ACD();
    public ACT Op = new ACT();

    //Уставки

    public ASG StrVal = new ASG();
    public ING OpDlTmms = new ING(); //500 мс?

    //Переменные

    private int cntTimeA = 0;
    private int cntTimeB = 0;
    private int cntTimeC = 0;



    @Override
    public void process() {
        boolean strA = A.getPhsA().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();
        boolean strB = A.getPhsB().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();
        boolean strC = A.getPhsC().getCVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();

        Str.getGeneral().setValue(strA || strB || strC);
        Str.getPhsA().setValue(strA);
        Str.getPhsB().setValue(strB);
        Str.getPhsC().setValue(strC);

        if (strA) cntTimeA++; else cntTimeA = 0;
        if (strB) cntTimeB++; else cntTimeB = 0;
        if (strC) cntTimeC++; else cntTimeC = 0;

        Op.getPhsA().setValue(cntTimeA * dt > OpDlTmms.getSetVal().getValue());
        Op.getPhsB().setValue(cntTimeB * dt > OpDlTmms.getSetVal().getValue());
        Op.getPhsC().setValue(cntTimeC * dt > OpDlTmms.getSetVal().getValue());
        Op.getGeneral().setValue(Op.getPhsA().getValue() || Op.getPhsB().getValue() || Op.getPhsC().getValue());


    }

    @Override
    public void build(String pref, String name, Integer id, String[] parameters) {
        StrVal.getSetMag().getF().setValue(Double.parseDouble(parameters[0]));
        OpDlTmms.getSetVal().setValue(Integer.parseInt(parameters[1]));
        this.pref = pref;
        this.clazz = name;
        this.inst = id;
    }

    @Override
    public <T extends LN> void connect(T logicNode) {
        if (logicNode instanceof MMXU mmxu){
            A = mmxu.A;
        } else {
            log.error("Не правильно задан ID логического узла в конфигурации связей");
        }
    }

    @Override
    public NHMISignal getSignal(String name, String parameters) {
        if (parameters.contains("trigger")){
            return new NHMISignal(name, Str.getPhsA());
        } else if(parameters.contains("setting")){
            return new NHMISignal(name, StrVal.getSetMag().getF());
        }
        return new NHMISignal(name, Op.getPhsA());
    }
}
