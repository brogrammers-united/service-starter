package org.bgu.model;

public enum PhoneType {

	CELL("Cell"), HOME("Home"), WORK("Work");
	
	private String type;
	
	private PhoneType(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
