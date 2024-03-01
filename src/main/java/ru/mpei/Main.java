package ru.mpei;

import ru.mpei.IEC61850.logicalNodes.comman.LN;
import ru.mpei.IEC61850.logicalNodes.measurements.MMXU;
import ru.mpei.IEC61850.logicalNodes.protection.PTOC;
import ru.mpei.IEC61850.logicalNodes.protocol.LSVS;

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
        ptoc.StrVal.getSetMag().getF().setValue(5000.0); // задаем уставку
        ptoc.OpDlTmms.getSetVal().setValue(500); // задаем время срабатывания
        logicalNodes.add(ptoc);

//        NHMI nhmi = new NHMI;
//        nhmi.addSignal("Токи", new NHMISignal("IaRMS", mmxu.A.getPhsA().getCVal().getMag().getF()
//         new NHMISignal("Уставка", ptoc.StrVal)

        while (lsvs.hasNext()) {
            logicalNodes.forEach(LN::process);
            System.out.printf(": ", lsvs.getOut().get(0).getInstMag().getF().getValue());
        }


    }
}