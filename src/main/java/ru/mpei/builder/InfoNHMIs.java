package ru.mpei.builder;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс конфигурации для объединения нескольких сигналов
 * в одно окно NHMI, name - название обобщения
 */
@Data
@XmlAccessorType(value = XmlAccessType.FIELD)
public class InfoNHMIs {
    @XmlAttribute(name = "name")
    private String name = "";
    @XmlElement(name="nhmi")
    private List<InfoNHMI> signalNHMI = new ArrayList<>();

}
