package io.github.marrafon91.DSLearn.enums;

public enum DeliverStatus {

    PENDING(0),
    ACCEPTED(1),
    REJECTED(2);

    private final Integer value;

    DeliverStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static DeliverStatus fromValue(Integer value) {
        for (DeliverStatus type : DeliverStatus.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid DeliverStatus value: " + value);
    }
}
