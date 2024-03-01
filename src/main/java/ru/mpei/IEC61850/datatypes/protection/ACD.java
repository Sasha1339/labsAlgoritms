package ru.mpei.IEC61850.datatypes.protection;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.common.Attribute;

@Setter
@Getter
public class ACD extends ACT{
    private Attribute<Direction> dirGeneral = new Attribute<>(Direction.unknown);
    private Attribute<Direction> derPhsA = new Attribute<>(Direction.unknown);
    private Attribute<Direction> derPhsB = new Attribute<>(Direction.unknown);
    private Attribute<Direction> derPhsC = new Attribute<>(Direction.unknown);
    private Attribute<Direction> derNeut = new Attribute<>(Direction.unknown);
}
