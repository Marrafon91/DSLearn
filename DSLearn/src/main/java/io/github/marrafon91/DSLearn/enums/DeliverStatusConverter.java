package io.github.marrafon91.DSLearn.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DeliverStatusConverter implements AttributeConverter<DeliverStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DeliverStatus deliverStatus) {
        if (deliverStatus == null) {
            return null;
        }
        return deliverStatus.getValue();
    }

    @Override
    public DeliverStatus convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return DeliverStatus.fromValue(value);
    }
}
