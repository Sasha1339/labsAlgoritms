package ru.mpei.IEC61850.utils;

import ru.mpei.IEC61850.datatypes.measurements.SAV;
import ru.mpei.IEC61850.datatypes.measurements.Vector;

/**
 * Родительский класс для фильтров
 */

public abstract class Filter {

    public abstract void process(SAV instMag, Vector vector);

}
