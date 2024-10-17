package com.alver.fatefall.fx.core.view;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Component
@Retention(RetentionPolicy.RUNTIME)
public @interface EditorInfo {

	String DEFAULT_DISPLAY_NAME = "";
	String displayName() default DEFAULT_DISPLAY_NAME;

	String DEFAULT_CATEGORY = "";
	String category() default DEFAULT_CATEGORY;

	int DEFAULT_ORDER = 0;
	int order() default DEFAULT_ORDER;

	boolean DEFAULT_IGNORE = false;
	boolean ignore() default DEFAULT_IGNORE;
}
