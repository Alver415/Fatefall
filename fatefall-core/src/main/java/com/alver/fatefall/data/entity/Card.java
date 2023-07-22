package com.alver.fatefall.data.entity;

public class Card extends Entity {

	private String name;
	protected String frontFxml;
	protected String backFxml;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getFrontFxml() {
		return frontFxml;
	}
	public void setFrontFxml(String frontFxml) {
		this.frontFxml = frontFxml;
	}

	public String getBackFxml() {
		return backFxml;
	}
	public void setBackFxml(String backFxml) {
		this.backFxml = backFxml;
	}

}
