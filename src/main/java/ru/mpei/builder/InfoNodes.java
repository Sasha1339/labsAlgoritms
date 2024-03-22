package ru.mpei.builder;

import lombok.Data;
import ru.mpei.IEC61850.logicalNodes.common.LNXml;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "infoNodes")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class InfoNodes {
    @XmlElementWrapper(name="logicalNodes")
    @XmlElement(name="LN")
    private List<LNXml> logicalNodes = new ArrayList<>();
    @XmlElementWrapper(name="links")
    @XmlElement(name="link")
    private List<Link> link = new ArrayList<>();

}
