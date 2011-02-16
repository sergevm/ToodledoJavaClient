package com.domaindriven.toodledo;

public class Task {

	private String id;
	private String title;
	private long modified;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setModified(long modified) {
		this.modified = modified;
	}

	public long getModified() {
		return modified;
	}
}
