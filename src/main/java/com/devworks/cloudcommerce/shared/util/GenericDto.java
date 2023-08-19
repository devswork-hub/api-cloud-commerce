package com.devworks.cloudcommerce.shared.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
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

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public static <T> GenericBuilder<GenericDto<T>> builder() {
        return GenericBuilder.of(GenericDto<T>::new);
    }

    public static <T> GenericDto<T> toDto(T model) {
        GenericDto<T> dto = GenericDto.<T>builder().build();

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

    public T toEntity(Supplier<T> entitySupplier) {
        T entity = entitySupplier.get();

        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (properties.containsKey(field.getName())) {
                field.setAccessible(true);
                try {
                    field.set(entity, properties.get(field.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return entity;
    }
}
