package ru.mpei.IEC61850.datatypes;

import lombok.Getter;
import lombok.Setter;

/**
 * Общий интерфейс типов данных по МЭК 61850
 */
@Getter
@Setter
public class Data {
    private String name;
    private String ref;

}
