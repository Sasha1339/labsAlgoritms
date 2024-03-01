package ru.mpei.IEC61850.datatypes.measurements;

import lombok.RequiredArgsConstructor;
import ru.mpei.IEC61850.datatypes.Data;
import ru.mpei.IEC61850.datatypes.common.Quality;
import ru.mpei.IEC61850.datatypes.common.Timestamp;

@lombok.Data
public class SAV extends Data {

    private AnalogueValue instMag = new AnalogueValue();
    private Quality q = new Quality();
    private Timestamp t = new Timestamp();

}
