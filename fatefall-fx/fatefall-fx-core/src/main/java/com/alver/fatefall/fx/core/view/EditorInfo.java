package com.alver.fatefall.fx.core.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EditorInfo {

	String DEFAULT_DISPLAY_NAME = "";
	String displayName() default DEFAULT_DISPLAY_NAME;

	String DEFAULT_CATEGORY = "";
	String category() default DEFAULT_CATEGORY;

	int DEFAULT_ORDER = Integer.MAX_VALUE / 2;
	int order() default DEFAULT_ORDER;

	boolean DEFAULT_IGNORE = false;
	boolean ignore() default DEFAULT_IGNORE;
}
