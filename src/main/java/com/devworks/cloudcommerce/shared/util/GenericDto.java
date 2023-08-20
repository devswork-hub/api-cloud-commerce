package com.devworks.cloudcommerce.shared.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;


/**
 * @implNote
 * User user = new User("John", "Doe", "john@example.com", "12345678901", "1234567890", "+1", "123");
 * GenericDTO<User> userDTO = GenericDTO.fromEntity(user);
 *
 * System.out.println("First Name: " + userDTO.getProperty("firstName"));
 * System.out.println("Last Name: " + userDTO.getProperty("lastName"));
*/
public class GenericDto<T> {
    private Map<String, Object> properties = new HashMap<>();

    private GenericDto() {}

    public String getProperty(String propertyName) {
        Object value = properties.get(propertyName);
        return Objects.toString(value, "");
    }

    public static <T> GenericBuilder<GenericDto<T>> builder() {
        return GenericBuilder.of(GenericDto<T>::new);
    }

    public static <T> GenericDto<T> toDto(T model) {
        GenericDto<T> dto = GenericDto.<T>builder().build();
        System.out.println(dto);
        System.out.println(model);

        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(model);
                dto.properties.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return dto;
    }

    public static <T> T toEntity(GenericDto<T> dto, Class<T> entityClass) {
        T entity = null;
        try {
            entity = entityClass.getDeclaredConstructor().newInstance();

            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                String propertyName = field.getName();
                Object value = dto.getProperty(propertyName);
                if (value != null) {
                    field.setAccessible(true);
                    field.set(entity, value);
                }
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return entity;
    }
}
