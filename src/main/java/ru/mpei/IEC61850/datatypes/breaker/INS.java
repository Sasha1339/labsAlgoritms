package ru.mpei.IEC61850.datatypes.breaker;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.Data;
import ru.mpei.IEC61850.datatypes.common.Attribute;
import ru.mpei.IEC61850.datatypes.common.Quality;
import ru.mpei.IEC61850.datatypes.common.Timestamp;

@Getter
@Setter
public class INS extends Data {

    private Attribute<Integer> stVal = new Attribute<>(0);
    private Quality q = new Quality();
    private Timestamp t = new Timestamp();

}
