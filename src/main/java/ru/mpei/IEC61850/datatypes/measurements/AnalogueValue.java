package ru.mpei.IEC61850.datatypes.measurements;

import ru.mpei.IEC61850.datatypes.Data;
import ru.mpei.IEC61850.datatypes.common.Attribute;

@lombok.Data
public class AnalogueValue extends Data {

    private Attribute<Double> f = new Attribute<>(0.0);

}
