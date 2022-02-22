package com.asdtest.exceptions;

public class Message {

	private String status;
	private String message;
	private String description;

	public Message(String status, String message, String decription) {
		super();
		this.status = status;
		this.message = message;
		this.description = decription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDecription() {
		return description;
	}

	public void setDecription(String decription) {
		this.description = decription;
	}

	@Override
	public String toString() {
		return "Message {status:" + status + ", message:" + message + ", description:" + description + "}";
	}
	
	

}
