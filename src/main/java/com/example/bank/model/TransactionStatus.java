package com.example.bank.model;

public class TransactionStatus {

	private boolean status;
	private String reason;
	
	public TransactionStatus(boolean status, String reason) {
		super();
		this.status = status;
		this.reason = reason;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
