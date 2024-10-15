package com.alver.fatefall.fx.core.view;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Component
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyEditorInfo {

	String displayName() default "";

	String category() default "";

	String[] categoryOrder() default {};

	int order() default 0;

	boolean ignore() default false;
}
