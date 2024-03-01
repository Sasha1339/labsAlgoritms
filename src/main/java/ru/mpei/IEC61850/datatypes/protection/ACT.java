package ru.mpei.IEC61850.datatypes.protection;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.Data;
import ru.mpei.IEC61850.datatypes.common.Attribute;
import ru.mpei.IEC61850.datatypes.common.Quality;
import ru.mpei.IEC61850.datatypes.common.Timestamp;

@Getter
@Setter
public class ACT extends Data {

    private Attribute<Boolean> general = new Attribute<>(false);
    private Attribute<Boolean> phsA = new Attribute<>(false);
    private Attribute<Boolean> phsB = new Attribute<>(false);
    private Attribute<Boolean> phsC = new Attribute<>(false);
    private Attribute<Boolean> neut = new Attribute<>(false);
    private Quality q = new Quality();
    private Timestamp t = new Timestamp();

}
