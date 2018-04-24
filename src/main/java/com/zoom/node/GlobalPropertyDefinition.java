package com.zoom.node;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by liangbo.zhou on 18-4-23.
 */
@Target(value = {ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface GlobalPropertyDefinition {
}
