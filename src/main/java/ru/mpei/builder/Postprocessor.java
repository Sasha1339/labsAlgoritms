package ru.mpei.builder;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import ru.mpei.IEC61850.logicalNodes.LN;
import ru.mpei.IEC61850.logicalNodes.hmi.NHMI;
import ru.mpei.IEC61850.logicalNodes.hmi.other.NHMISignal;
import ru.mpei.IEC61850.logicalNodes.protocol.LSVS;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Класс автоматического создания объектов из конфигурации
 * автоматической настройки связей между объектами
 * автоматической настройки сигналов NHMI
 */

public class Postprocessor {
    private static Map<Integer, LN> objectsLogicNodes = new HashMap<>();

    private static InfoNodes infoNodes = new InfoNodes();

    private static List<LN> logicalNodesList = new ArrayList<>();


    static {
        getInfo();
        build();
        setNHMI();
        execute();
    }

    /**
     * считывание конфигурационного файла
     */
    @SneakyThrows
    private static void getInfo(){
        JAXBContext context = JAXBContext.newInstance(InfoNodes.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        infoNodes = (InfoNodes) unmarshaller.unmarshal(
                new File("src/main/resources/data.xml")
        );
    }

    /**
     * настройка NHMI сигналов в соответствии с конфигурацией
     */
    public static void setNHMI(){
        NHMI nhmi = new NHMI(0.250);
        infoNodes.getSignalNHMIs().forEach(e -> {
            List<NHMISignal> signals = new ArrayList<>();
            for (InfoNHMI info: e.getSignalNHMI()){
                signals.add(objectsLogicNodes.get(info.getId()).getSignal(info.getName(), info.getParameters()));
            }
            nhmi.addSignals(e.getName(), signals);
        });
        logicalNodesList.add(nhmi);
    }

    /**
     * основной метод автоматического создания объектов LN
     */
    public static void build(){

        Reflections reflections = new Reflections(LN.class);
        Set<Class<? extends LN>> logicalNodes = reflections.getSubTypesOf(LN.class);

        infoNodes.getLogicalNodes().forEach(e -> {
            Optional<Class<? extends LN>> lNode = logicalNodes.stream()
                    .filter(l -> l.getName().contains(e.getClazz()))
                    .findFirst();
            if (lNode.isEmpty()){
                return;
            }
            LN object = null;
            try {
                object = lNode.get().getDeclaredConstructor().newInstance();
                if (e.getOtherParameters() != null){
                    object.build(e.getPref(),
                            e.getClazz(),
                            e.getInst(),
                            e.getOtherParameters().replace(" ", "").split(";"));
                } else {
                    object.build(e.getPref(),
                            e.getClazz(),
                            e.getInst(),
                            null);
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
            objectsLogicNodes.put(e.getInst(), object);
            });

        infoNodes.getLink().forEach(l -> {
            if (objectsLogicNodes.get(l.getSource()).getClass() == LSVS.class){
                logicalNodesList.add(objectsLogicNodes.get(l.getSource()));
            }

            LN ln = objectsLogicNodes
                    .get(l.getDestination());

            ln.connect(objectsLogicNodes.get(l.getSource()));
            if (!logicalNodesList.contains(ln)){
                logicalNodesList.add(ln);
            }

        });
    }

    /**
     * Выполнение процессов
     */
    public static void execute(){
        LSVS lsvs = (LSVS) logicalNodesList.stream().filter(e -> e.getClass() == LSVS.class).findFirst().orElse(new LSVS());
        while (lsvs.hasNext()) {
            logicalNodesList.forEach(LN::process);
        }
    }

}
