package ru.mpei.IEC61850.logicalNodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(value = XmlAccessType.FIELD)
public abstract class LN {

    @XmlAttribute(name = "prefix")
    protected String pref; //дополнение в название
    @XmlAttribute(name = "name")
    protected String clazz; // название класса MMXU например
    @XmlAttribute(name = "id")
    protected int inst;

    public abstract void process(); // рассчет

    public abstract void build(String pref, String name, Integer id, String[] parameters); // autobuild

    public abstract <T extends LN> void connect(T logicNode); // autoconnect

}
