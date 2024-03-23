package ru.mpei.IEC61850.datatypes.common;

import ru.mpei.IEC61850.datatypes.Data;

/**
 * Класс временной метки
 */

@lombok.Data
public class Timestamp extends Data {

    private long value;

}
