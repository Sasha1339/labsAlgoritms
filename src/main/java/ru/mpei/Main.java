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
import ru.mpei.builder.Postprocessor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<LN> logicalNodes = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        new Postprocessor();

    }
}