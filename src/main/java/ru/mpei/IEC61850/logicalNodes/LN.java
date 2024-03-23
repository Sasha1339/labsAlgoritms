package ru.mpei.IEC61850.logicalNodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mpei.IEC61850.datatypes.common.Attribute;
import ru.mpei.IEC61850.logicalNodes.hmi.other.NHMISignal;

import javax.xml.bind.annotation.*;

/**
 * Родительский класс логического узла
 *
 */
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
    protected int inst; // идентификатор в мках одного LD

    /**
     * Метод для расчетов, сравнения уставок
     */
    public abstract void process(); // рассчет

    /**
     * метод построения объекта с набором параметров
     * @param pref - префикс
     * @param name - название LN
     * @param id - идентификатор
     * @param parameters - дополнительные параметры, далее определяются в конкретном LN
     */
    public abstract void build(String pref, String name, Integer id, String[] parameters); // autobuild

    /**
     * метод установки связи между LN
     * @param logicNode - LN с которым нужно связать этот LN
     * @param <T> - тип LN с которым связываем
     */
    public abstract <T extends LN> void connect(T logicNode); // autoconnect

    /**
     * Метод для создания NHMI Signal в зависиомти от parameters
     * @param name - название сигнала
     * @param parameters - дополнительные не обязательные парамтеры
     * @return готовый NHMI Signal для добавления в осциллограммы
     */
    public abstract NHMISignal getSignal(String name,String parameters);

}
