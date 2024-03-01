package ru.mpei.IEC61850.datatypes.measurements;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.Data;

@Setter
@Getter
public class Vector extends Data {

    private AnalogueValue mag = new AnalogueValue();
    private AnalogueValue ang = new AnalogueValue();


}
