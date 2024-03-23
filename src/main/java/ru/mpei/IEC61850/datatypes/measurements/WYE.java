package ru.mpei.IEC61850.datatypes.measurements;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.Data;

/**
 * Класс хранения векторов фаз и нейтрали (действующих значений)
 */
@Getter
@Setter
public class WYE extends Data {

    private CMV phsA = new CMV();
    private CMV phsB = new CMV();
    private CMV phsC = new CMV();
    private CMV neut = new CMV();

}
