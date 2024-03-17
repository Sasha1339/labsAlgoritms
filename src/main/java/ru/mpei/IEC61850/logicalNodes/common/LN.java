package ru.mpei.IEC61850.logicalNodes.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class LN {

    private String pref; //дополнение в название
    private String clazz; // название класса MMXU например
    private int inst;

    public abstract void process(); // рассчет

}
