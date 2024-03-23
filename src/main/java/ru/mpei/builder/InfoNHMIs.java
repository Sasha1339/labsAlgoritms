package ru.mpei.builder;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlAccessorType(value = XmlAccessType.FIELD)
public class InfoNHMIs {
    @XmlAttribute(name = "name")
    private String name = "";
    @XmlElement(name="nhmi")
    private List<InfoNHMI> signalNHMI = new ArrayList<>();

}
