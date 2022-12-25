package com.example.springwebapp.PostProcessing;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Audited {
//    String DEFAULT_PERFORMANCE_LOGGER = "PERFORMANCE";
//  https://blog.pchudzik.com/201902/beanpostprocessor/
    String value() default "Audited";
}
