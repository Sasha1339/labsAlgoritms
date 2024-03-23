package ru.mpei.builder;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Класс используемый для конфигурации единичного сигнала
 * NHMI, в дальнейшем информация используется
 * для отрисовки осциллограмм
 */
@Data
@XmlAccessorType(value = XmlAccessType.FIELD)
public class InfoNHMI {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "id")
    private int id;
    @XmlAttribute(name = "parameters")
    private String parameters;
}
