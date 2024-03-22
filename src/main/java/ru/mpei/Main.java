package ru.mpei;

import ru.mpei.IEC61850.logicalNodes.breaker.CSWI;
import ru.mpei.IEC61850.logicalNodes.breaker.XCBR;
import ru.mpei.IEC61850.logicalNodes.LN;
import ru.mpei.IEC61850.logicalNodes.hmi.NHMI;
import ru.mpei.IEC61850.logicalNodes.hmi.other.NHMISignal;
import ru.mpei.IEC61850.logicalNodes.measurements.MMXU;
import ru.mpei.IEC61850.logicalNodes.protection.PTOC;
import ru.mpei.IEC61850.logicalNodes.protocol.LSVS;
import ru.mpei.IEC61850.logicalNodes.time.Time;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<LN> logicalNodes = new ArrayList<>();

    public static void main(String[] args) throws Exception {


        LSVS lsvs = new LSVS();
        lsvs.setPath("C:\\Users\\serge\\OneDrive\\Рабочий стол\\1 курс магистратура, Э-13м-23\\Алгоритмы РЗА\\Опыты\\Начало линии\\");
        lsvs.setFileName("PhA80");
        logicalNodes.add(lsvs);

        MMXU mmxu = new MMXU();
        mmxu.IaInst = lsvs.getOut().get(0);
        mmxu.IbInst = lsvs.getOut().get(1);
        mmxu.IcInst = lsvs.getOut().get(2);
        logicalNodes.add(mmxu);

        PTOC ptoc = new PTOC();
        ptoc.A = mmxu.A;
        ptoc.StrVal.getSetMag().getF().setValue(0.1); // задаем уставку
        ptoc.OpDlTmms.getSetVal().setValue(500); // задаем время срабатывания
        logicalNodes.add(ptoc);

        CSWI cswi = new CSWI();
        cswi.OpOpns.add(ptoc.Op);
        logicalNodes.add(cswi);

        XCBR xcbr = new XCBR();
        xcbr.Pos = cswi.Pos;
        logicalNodes.add(xcbr);

        Time time = new Time();
        logicalNodes.add(time);

        NHMI nhmi = new NHMI();
//        nhmi.addSignal("Токи", new NHMISignal("IaRMS", mmxu.A.getPhsA().getCVal().getMag().getF()
//         new NHMISignal("Уставка", ptoc.StrVal)

        nhmi.addSignals(
            new NHMISignal("Ia",time.t, mmxu.IaInst.getInstMag().getF()),
             new NHMISignal("Срабатывание",time.t, ptoc.Str.getPhsA()),
               new NHMISignal("Время",time.t, ptoc.Op.getPhsA()),
//
        new NHMISignal("Выключатель",time.t, cswi.OpOpn.getGeneral())
        );

        logicalNodes.add(nhmi);

        while (lsvs.hasNext()) {
            logicalNodes.forEach(LN::process);
            //System.out.printf(": ", lsvs.getOut().get(0).getInstMag().getF().getValue());
        }


    }
}