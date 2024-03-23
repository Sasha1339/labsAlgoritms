package ru.mpei.IEC61850.logicalNodes.common;


import lombok.Data;
import ru.mpei.IEC61850.logicalNodes.LN;
import ru.mpei.IEC61850.logicalNodes.hmi.other.NHMISignal;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Класс представления кофигурации в виде XML для логических узлов
 */
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

    @Override
    public NHMISignal getSignal(String name,String parameters) {
        return null;
    }
}
