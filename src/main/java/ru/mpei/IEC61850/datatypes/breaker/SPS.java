package ru.mpei.IEC61850.datatypes.breaker;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.common.Attribute;
import ru.mpei.IEC61850.datatypes.common.Quality;
import ru.mpei.IEC61850.datatypes.common.Timestamp;


/**
 * Класс хранения информации в виде boolean
 * single point status
 */
@Getter
@Setter
public class SPS {
    private Attribute<Boolean> stVal = new Attribute<>(false);
    private Quality q = new Quality();
    private Timestamp t = new Timestamp();

}
