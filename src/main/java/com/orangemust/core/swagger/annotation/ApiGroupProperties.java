package com.orangemust.core.swagger.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 */
@Documented
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ApiGroupProperties {
    ApiGroupProperty[] value();
}
