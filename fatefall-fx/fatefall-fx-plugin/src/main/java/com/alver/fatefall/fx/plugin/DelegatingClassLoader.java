package com.alver.fatefall.fx.plugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class DelegatingClassLoader extends ClassLoader {

	private static final Logger log = LoggerFactory.getLogger(DelegatingClassLoader.class);
	protected final List<ClassLoader> classLoaders;

	public DelegatingClassLoader(List<ClassLoader> classLoaders) {
		this.classLoaders = classLoaders;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(name);
		} catch (ClassNotFoundException e) {
			Iterator<ClassLoader> iterator = classLoaders.iterator();
			while (iterator.hasNext()) {
				try {
					return iterator.next().loadClass(name);
				} catch (ClassNotFoundException _) {
				}
			}
			throw e;
		}
	}

}
