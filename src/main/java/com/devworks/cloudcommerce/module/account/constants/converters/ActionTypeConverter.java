package com.devworks.cloudcommerce.module.account.constants.converters;

import com.devworks.cloudcommerce.module.account.constants.ActionType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ActionTypeConverter implements AttributeConverter<ActionType, String> {
    @Override
    public String convertToDatabaseColumn(ActionType attribute) {
        if(attribute == null)
            return null;
        return attribute.getName();
    }

    @Override
    public ActionType convertToEntityAttribute(String type) {
        if (type == null) return null;

        return Stream.of(ActionType.values())
            .filter(a -> a.getName().equals(type))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
