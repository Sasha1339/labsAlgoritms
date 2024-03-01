package ru.mpei.IEC61850.logicalNodes.measurements;

import ru.mpei.IEC61850.datatypes.measurements.SAV;
import ru.mpei.IEC61850.datatypes.measurements.WYE;
import ru.mpei.IEC61850.logicalNodes.comman.LN;
import ru.mpei.IEC61850.utils.Filter;
import ru.mpei.IEC61850.utils.MsdFilter;

import java.lang.reflect.Field;

public class MMXU extends LN {
    //в будущем здесь будет фильтр Фурье

    public static int bufSize = 80;

    //входы
    public SAV UaInst = new SAV(); //Inst мгновеннеы величины
    public SAV UbInst = new SAV();
    public SAV UcInst = new SAV();

    public SAV IaInst = new SAV();
    public SAV IbInst = new SAV();
    public SAV IcInst = new SAV();

    //выходы

    public WYE A = new WYE();

    //переменные

    private final Filter ia = new MsdFilter(bufSize);

    private final Filter ib = new MsdFilter(bufSize);
    private final Filter ic = new MsdFilter(bufSize);
    @Override
    public void process() {
        this.ia.process(this.IaInst, A.getPhsA().getCVal());
        this.ib.process(this.IbInst, A.getPhsB().getCVal());
        this.ic.process(this.IcInst, A.getPhsC().getCVal());
    }
}
