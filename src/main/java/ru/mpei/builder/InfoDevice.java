package ru.mpei.builder;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "devices")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class InfoDevice {

    @XmlElementWrapper(name="device")
    @XmlElement(name="infoNodes")
    private List<InfoNodes> infoNodes = new ArrayList<>();

}
