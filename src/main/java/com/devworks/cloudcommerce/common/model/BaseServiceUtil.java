package com.devworks.cloudcommerce.common.model;

/**
 * Esta é a classe base para serviços utilitários.
 * Subclasses devem chamar explicitamente o construtor desta classe ao estender.
 */
public abstract class BaseServiceUtil {
    protected BaseServiceUtil() {
        throw new IllegalStateException("You must call the constructor from a subclass.");
    }
}