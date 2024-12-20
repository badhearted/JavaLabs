package org.somepackage;

import java.lang.annotation.*;

/**
 * Аннотация {@code AutoInjectable} используется для пометки полей, в которые должно быть автоматически выполнено внедрение зависимостей.
 * Эта аннотация позволяет инъектору {@link Injector} определить, какие поля должны быть заполнены зависимостями при создании объекта.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInjectable {}
