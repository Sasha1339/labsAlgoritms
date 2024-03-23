package ru.mpei.IEC61850.datatypes.breaker;


/**
 * Тип данных, определяющий контроль управления
 * в соответствии с безопасностью МЭК 61850 7-2
 */
public enum CtlModel {
    status_only,
    direct_with_normal_security,
    sbo_with_normal_security,
    direct_with_enhanced_security,
    sbo_with_enhanced_security
}
