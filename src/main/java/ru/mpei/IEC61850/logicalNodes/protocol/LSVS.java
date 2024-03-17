package ru.mpei.IEC61850.logicalNodes.protocol;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.measurements.SAV;
import ru.mpei.IEC61850.logicalNodes.common.LN;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter @Setter

public class LSVS extends LN {

    private String path;
    private String fileName;

    private List<String> cfgFileList = new ArrayList<>();
    private List<String> datFileList = new ArrayList<>();

    private List<Double> kAList = new ArrayList<>();
    private List<Double> kBList = new ArrayList<>();

    private int analogNumber = 0;
    private int digitalNumber = 0;

    private Iterator<String> datIterator;

    private final List<SAV> out = new ArrayList<>();

    public LSVS() {
        for (int i = 0; i < 20; i++){
            out.add(new SAV());
        }
    }

    @Override
    public void process() {
        if (this.datIterator.hasNext()) {
            String[] str = this.datIterator.next().split(",");
            for (int i = 2, j = 0; i < this.analogNumber + 2; i++,  j++) {
                double value = Double.parseDouble(str[i])*this.kAList.get(j) + this.kBList.get(j);
                this.out.get(j).getInstMag().getF().setValue(value);
            }
        }
    }

    public boolean hasNext() {
        return this.datIterator.hasNext();
    }

    public void setFileName(String fileName) throws Exception{
        this.fileName = fileName;

        String cfgPath = path + fileName + ".cfg";
        String datPath = path + fileName + ".dat";

        File cfgFile = new File(cfgPath);
        File datFile = new File(datPath);

        if (!cfgFile.exists()) throw new Exception("Путь к файлу указан не верно!");
        if (!datFile.exists()) throw new Exception("Путь к файлу указан не верно!");

        this.cfgFileList = Files.readAllLines(cfgFile.toPath());
        this.datFileList = Files.readAllLines(datFile.toPath());

        String strNumber = this.cfgFileList.get(1)
                .replace("A", "")
                .replace("D", "");

        this.analogNumber = Integer.parseInt(strNumber.split(",")[1]);
        this.digitalNumber = Integer.parseInt(strNumber.split(",")[2]);

        for (int i = 2; i < this.analogNumber + 2; i++){
            double kA = Double.parseDouble(this.cfgFileList.get(i).split(",")[5]);
            double kB = Double.parseDouble(this.cfgFileList.get(i).split(",")[6]);

            this.kAList.add(kA);
            this.kBList.add(kB);
        }

        this.datIterator = this.datFileList.iterator();

    }

}
