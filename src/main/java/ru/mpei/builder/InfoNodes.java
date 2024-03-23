package ru.mpei.builder;

import lombok.Data;
import ru.mpei.IEC61850.logicalNodes.common.LNXml;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Основной класс конфигурации логических узлов
 * logicalNodes - объявление требуемых логических узлов
 * link - объявление и настройка связей между логическими узлами
 * signalNHMIs - конфигурация NHMI Signals
 */
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

    @XmlElementWrapper(name="signals")
    @XmlElement(name="nhmis")
    private List<InfoNHMIs> signalNHMIs = new ArrayList<>();
}
