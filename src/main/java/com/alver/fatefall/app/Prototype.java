package com.alver.fatefall.app;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Bean
@Component
@Scope(SCOPE_PROTOTYPE)
public @interface Prototype {

	@AliasFor(annotation = Bean.class)
	String[] value() default {};

	@AliasFor(annotation = Bean.class)
	String[] name() default {};

	@AliasFor(annotation = Bean.class)
	boolean autowireCandidate() default true;

	@AliasFor(annotation = Bean.class)
	String initMethod() default "";

	@AliasFor(annotation = Bean.class)
	String destroyMethod() default AbstractBeanDefinition.INFER_METHOD;
}
