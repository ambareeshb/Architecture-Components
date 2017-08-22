package com.example.ambareeshb.payukickstarter.DI;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ambareesh on 22/8/17.
 */

public class Qualifiers {
    @Qualifier
    @Documented
    @Retention(RUNTIME)
    public @interface Named {
        String value() default "";

    }
}
