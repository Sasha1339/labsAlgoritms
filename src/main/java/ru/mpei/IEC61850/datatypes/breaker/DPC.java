package ru.mpei.IEC61850.datatypes.breaker;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.Data;
import ru.mpei.IEC61850.datatypes.common.Attribute;
import ru.mpei.IEC61850.datatypes.common.Quality;
import ru.mpei.IEC61850.datatypes.common.Timestamp;

/**
 * Класс хранения данных о состоянии CodedEnum
 */
@Getter
@Setter
public class DPC extends Data {
    private Attribute<CodedEnum> stVal = new Attribute<>(CodedEnum.on);
    private Quality q = new Quality();
    private Timestamp t = new Timestamp();

}
