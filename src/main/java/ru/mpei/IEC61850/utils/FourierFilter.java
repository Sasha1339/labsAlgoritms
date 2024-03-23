package ru.mpei.IEC61850.utils;

import ru.mpei.IEC61850.datatypes.measurements.SAV;
import ru.mpei.IEC61850.datatypes.measurements.Vector;

public class FourierFilter extends Filter{

    private final double[] buffer;

    private int cnt = 0;

    private double a = 0.0;
    private double b = 0.0;
    private double k = 1.0;

    private double harmonicNumber = 1.0;

    public FourierFilter(int bufSize, double harmonicNumber) {
        this.buffer = new double[bufSize];
        this.k = 2.0/bufSize;
        this.harmonicNumber = harmonicNumber;
    }

    public FourierFilter(int bufSize) {
        this.buffer = new double[bufSize];
        this.k = 2.0/bufSize;
    }


    @Override
    public void process(SAV instMag, Vector vector) {
        double a = 0.0;
        double b = 0.0;
        this.buffer[cnt] = instMag.getInstMag().getF().getValue();

        for (int i = 0; i < buffer.length; i++) {
            a = a + buffer[i] * Math.sin(harmonicNumber * 2 * Math.PI * 50 * i * (0.02 / buffer.length));
            b = b + buffer[i] * Math.cos(harmonicNumber * 2 * Math.PI * 50 * i * (0.02 / buffer.length));
        }
        a = k * a;
        b = k * b;

        vector.getMag().getF().setValue(Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)));
        vector.getAng().getF().setValue(Math.atan(b / a));
        if (++cnt >= buffer.length){
            cnt = 0;
        }
    }
}
