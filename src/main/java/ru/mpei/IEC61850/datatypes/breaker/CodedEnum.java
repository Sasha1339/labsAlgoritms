package ru.mpei.IEC61850.datatypes.breaker;

public enum CodedEnum {
    intermediate_state(0),
    off(1),
    on(2),
    bad_state(3);
    private final int value;
    private CodedEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
