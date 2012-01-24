package com.domaindriven.toodledo;

public class Task {

	private String id;
	private String title;
	private long modified;
	private long dueDate;
	private String note;
	private long completed;

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

	public void setCompleted(long completed) {
		this.completed = completed;
	}

	public long getCompleted() {
		return completed;
	}

	public long getDueDate() {
		return dueDate;
	}

	public void setDueDate(long dueTime) {
		this.dueDate = dueTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
