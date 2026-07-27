package org.springblade.common.groovy.annotation;
import java.lang.annotation.*;
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionalOnExistingProperty {
    String value();
}
