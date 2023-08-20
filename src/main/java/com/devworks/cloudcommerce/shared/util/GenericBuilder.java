package com.devworks.cloudcommerce.shared.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @implNote  <T>
 *     var test = GenericBuilder.of(User::new)
 * 			.with(User::setPhoneNumber, "123")
 * 			.build();
 *
 * 		System.out.println(test.getPhoneNumber());
 */
public class GenericBuilder<T> {
    private final Supplier<T> instantiation;
    private List<Consumer<T>> instanceModifiers = new ArrayList<>();

    public GenericBuilder(Supplier<T> instantiate) {
        this.instantiation = instantiate;
    }

    public static <T> GenericBuilder<T> of(Supplier<T> instantiator) {
        return new GenericBuilder<T>(instantiator);
    }

    public <U> GenericBuilder<T> with(BiConsumer<T, U> consumer, U value) {
        Consumer<T> c = instance -> consumer.accept(instance, value != null ? value : null);
        instanceModifiers.add(c);
        return this;
    }

    public T build() {
        T value = instantiation.get();
        instanceModifiers.forEach(modifier -> modifier.accept(value));
        instanceModifiers.clear();
        return value;
    }
}