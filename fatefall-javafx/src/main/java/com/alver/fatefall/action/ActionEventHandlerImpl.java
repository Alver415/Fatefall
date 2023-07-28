package com.alver.fatefall.action;

public abstract class ActionEventHandlerImpl implements ActionEventHandler {

	private final String title;

	public ActionEventHandlerImpl(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}
}