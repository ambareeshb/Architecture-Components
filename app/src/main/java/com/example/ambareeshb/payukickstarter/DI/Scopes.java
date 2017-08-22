package com.example.ambareeshb.payukickstarter.DI;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ambareesh on 21/8/17.
 */

public class Scopes {
    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ActivityScope {
    }
    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FragmentScope {
    }
}
