package io.github.marrafon91.DSLearn.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ResourceTypeConverter implements AttributeConverter<ResourceType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ResourceType resourceType) {
        if (resourceType == null) {
            return null;
        }
        return resourceType.getValue();
    }

    @Override
    public ResourceType convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return ResourceType.fromValue(value);
    }
}
