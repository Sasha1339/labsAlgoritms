package ru.mpei.IEC61850.logicalNodes.common;


import lombok.Data;
import ru.mpei.IEC61850.logicalNodes.LN;

import javax.xml.bind.annotation.XmlAttribute;

@Data
public class LNXml extends LN {

    @XmlAttribute(name = "parameters")
    private String otherParameters;

    @Override
    public void process() {

    }

    @Override
    public void build(String pref, String name, Integer id, String[] parameters) {
        this.pref = pref;
        this.clazz = name;
        this.inst = id;
    }

    @Override
    public <T extends LN> void connect(T logicNode) {
    }
}
