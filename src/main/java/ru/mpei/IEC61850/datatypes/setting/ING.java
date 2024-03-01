package ru.mpei.IEC61850.datatypes.setting;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.Data;
import ru.mpei.IEC61850.datatypes.common.Attribute;

@Getter
@Setter
public class ING extends Data {

    private Attribute<Integer> setVal = new Attribute<>(0);

}
