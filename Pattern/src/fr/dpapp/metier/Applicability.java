package fr.dpapp.metier;

import java.io.Serializable;

public class Applicability implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value;

	public Applicability(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
