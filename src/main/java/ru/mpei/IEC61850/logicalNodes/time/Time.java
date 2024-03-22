package ru.mpei.IEC61850.logicalNodes.time;

import ru.mpei.IEC61850.datatypes.common.Attribute;
import ru.mpei.IEC61850.logicalNodes.LN;

public class Time extends LN {

    public Attribute<Double> t = new Attribute<>(0.0);
    //переменные

    private double dt = 0.250;
    private int countProcessCompleted = 0;
    @Override
    public void process() {
        countProcessCompleted++;
        t.setValue(countProcessCompleted * dt);
        System.out.println(t.getValue());
    }

    @Override
    public void build(String pref, String name, Integer id, String[] parameters) {
        this.pref = pref;
        this.clazz = name;
        this.inst = id;
    }

    @Override
    public <T extends LN> void connect(T logicNode) {
    }
}
