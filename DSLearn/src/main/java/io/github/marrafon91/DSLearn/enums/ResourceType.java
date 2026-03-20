package io.github.marrafon91.DSLearn.enums;

public enum ResourceType {

    LESSON_ONLY(0),
    LESSON_TASK(1),
    FORUM(2),
    EXTERNAL_LINK(3);

    private final Integer value;

    ResourceType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static ResourceType fromValue(Integer value) {
        for (ResourceType type : ResourceType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid ResourceType value: " + value);
    }
}
