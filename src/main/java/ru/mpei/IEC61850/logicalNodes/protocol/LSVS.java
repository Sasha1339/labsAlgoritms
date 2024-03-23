package ru.mpei.IEC61850.logicalNodes.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import ru.mpei.IEC61850.datatypes.measurements.SAV;
import ru.mpei.IEC61850.logicalNodes.LN;
import ru.mpei.IEC61850.logicalNodes.hmi.other.NHMISignal;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Getter @Setter

public class LSVS extends LN {

    private String pathStart = "C:\\Users\\serge\\OneDrive\\Рабочий стол\\1 курс магистратура, Э-13м-23\\Алгоритмы РЗА\\Опыты\\Начало линии\\";

    private String pathEnd = "C:\\Users\\serge\\OneDrive\\Рабочий стол\\1 курс магистратура, Э-13м-23\\Алгоритмы РЗА\\Опыты\\Конец линии\\";

    private String path;
    private String fileName;

    private List<String> csvFileList = new ArrayList<>();

    private int analogNumber = 0;
    private int digitalNumber = 0;

    private Iterator<String> csvIterator;

    private final List<SAV> out = new ArrayList<>();

    public LSVS() {
        for (int i = 0; i < 20; i++){
            out.add(new SAV());
        }
    }

    @Override
    public void process() {
        if (this.csvIterator.hasNext()) {
            String[] str = this.csvIterator.next().split(",");
            for (int i = 1, j = 0; i < this.analogNumber + 1; i++, j++) {
                double value = Double.parseDouble(str[i]);
                this.out.get(j).getInstMag().getF().setValue(value);
            }
        }
    }

    @Override
    @SneakyThrows
    public void build(String pref, String name, Integer id, String[] parameters) {
        if (parameters[0].contains("end") || parameters[0].contains("конец")){
            setPath(this.pathEnd);
        } else {
            setPath(this.pathStart);
        }
        setFileName(parameters[1]);
        this.pref = pref;
        this.clazz = name;
        this.inst = id;
    }

    @Override
    public <T extends LN> void connect(T logicNode) {
    }

    @Override
    public NHMISignal getSignal(String name, String parameters) {
        return null;
    }

    public boolean hasNext() {
        return this.csvIterator.hasNext();
    }

    public void setFileName(String fileName) throws Exception{
        this.fileName = fileName;

        String csvPath = path + fileName + ".csv";

        File csvFile = new File(csvPath);

        if (!csvFile.exists()) throw new Exception("Путь к файлу указан не верно!");

        this.csvFileList = Files.readAllLines(csvFile.toPath());

        String strNumber = this.csvFileList.get(0);

        this.analogNumber = Arrays.stream(strNumber.split(",")).filter(e -> e.contains("Branch")).toList().size();
        this.digitalNumber = Arrays.stream(strNumber.split(",")).filter(e -> e.contains("CTLs")).toList().size();

        this.csvIterator = this.csvFileList.iterator();
        this.csvIterator.next();
    }

}
