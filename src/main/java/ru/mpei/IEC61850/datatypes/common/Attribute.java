package ru.mpei.IEC61850.datatypes.common;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Attr;

@Setter @Getter
public class Attribute<T> {

    private T value;

    public Attribute(T value) {
        this.value = value;
    }

}
