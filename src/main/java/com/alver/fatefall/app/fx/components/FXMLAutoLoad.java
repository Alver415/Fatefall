package com.alver.fatefall.app.fx.components;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FXMLAutoLoad {
    String NOT_PROVIDED = "NOT_PROVIDED";

    String location() default NOT_PROVIDED;
}
