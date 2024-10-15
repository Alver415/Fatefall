package com.alver.fatefall.fx.core.view;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Component
@Retention(RetentionPolicy.RUNTIME)
public @interface EditorInfo {

	String[] categories() default {};

}
