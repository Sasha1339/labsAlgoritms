package ru.mpei.builder;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import ru.mpei.IEC61850.logicalNodes.LN;
import ru.mpei.IEC61850.logicalNodes.protocol.LSVS;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class Postprocessor {
    private Map<Integer, LN> objectsLogicNodes = new HashMap<>();

    private List<LN> logicalNodesList = new ArrayList<>();


    @SneakyThrows
    private InfoNodes getInfo(){
        JAXBContext context = JAXBContext.newInstance(InfoNodes.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InfoNodes infoNodes = (InfoNodes) unmarshaller.unmarshal(
                new File("src/main/resources/data.xml")
        );
        return infoNodes;
    }

    public void execute(){

        Reflections reflections = new Reflections(LN.class);
        Set<Class<? extends LN>> logicalNodes = reflections.getSubTypesOf(LN.class);
        InfoNodes infoNodes = getInfo();

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
                            e.getOtherParameters().replace(" ", "").split(","));
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

            logicalNodesList.add(ln);
        });
    }

    public void process(){
        LSVS lsvs = (LSVS) logicalNodesList.stream().filter(e -> e.getClass() == LSVS.class).findFirst().orElse(new LSVS());
        while (lsvs.hasNext()) {
            logicalNodesList.forEach(LN::process);
        }
    }

}
