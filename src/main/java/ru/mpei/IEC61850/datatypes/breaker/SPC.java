package ru.mpei.IEC61850.datatypes.breaker;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.common.Attribute;

@Setter
@Getter
public class SPC {
    private Attribute<CtlModel> ctlModel = new Attribute<>(CtlModel.status_only);

}
