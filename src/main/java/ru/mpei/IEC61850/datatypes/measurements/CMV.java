package ru.mpei.IEC61850.datatypes.measurements;

import lombok.Getter;
import lombok.Setter;
import ru.mpei.IEC61850.datatypes.Data;
import ru.mpei.IEC61850.datatypes.common.Quality;
import ru.mpei.IEC61850.datatypes.common.Timestamp;

/**
 * Класс хранения данных в виде вектора (амплитуды и фазы)
 */
@Getter
@Setter
public class CMV extends Data {

    private Vector cVal = new Vector();
    private Quality q = new Quality();
    private Timestamp t = new Timestamp();

}
