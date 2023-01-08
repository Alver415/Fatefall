package com.alver.fatefall.api.models;

import com.alver.fatefall.persistence.FileExtension;

@FileExtension(".workspace")
public class Workspace {

	protected String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
