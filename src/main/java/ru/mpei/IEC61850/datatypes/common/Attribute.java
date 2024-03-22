package ru.mpei.IEC61850.datatypes.common;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Attribute<T> {

    private T value;

    public Attribute(T value) {
        this.value = value;
    }

}
