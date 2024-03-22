package ru.mpei.IEC61850.datatypes.protection;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.common.Attribute;

@Setter
@Getter
public class ACD extends ACT{
    private Attribute<Direction> dirGeneral = new Attribute<>(Direction.unknown);
    private Attribute<Direction> dirPhsA = new Attribute<>(Direction.unknown);
    private Attribute<Direction> dirPhsB = new Attribute<>(Direction.unknown);
    private Attribute<Direction> dirPhsC = new Attribute<>(Direction.unknown);
    private Attribute<Direction> dirNeut = new Attribute<>(Direction.unknown);
}
