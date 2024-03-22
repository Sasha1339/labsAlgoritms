package ru.mpei.builder;


import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Link {
    @XmlAttribute(name = "source")
    private int source;
    @XmlAttribute(name = "dest")
    private int destination;

}
