package com.github.lfexecleasy.anno;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LFColume {
    String value() default "";
}
