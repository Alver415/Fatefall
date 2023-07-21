package com.alver.fatefall.app.fx.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FXMLAutoLoad {
    String location() default "";

}
