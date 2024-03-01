package ru.mpei.IEC61850.datatypes.setting;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.Data;
import ru.mpei.IEC61850.datatypes.measurements.AnalogueValue;

@Getter
@Setter
public class ASG extends Data {

    private AnalogueValue setMag = new AnalogueValue();


}
