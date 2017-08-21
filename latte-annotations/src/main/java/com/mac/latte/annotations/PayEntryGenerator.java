package com.mac.latte.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by douliu on 2017/8/21.
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface PayEntryGenerator {
    String packageName();

    Class<?> entryTemplate();
}
