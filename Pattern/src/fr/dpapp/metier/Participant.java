package fr.dpapp.metier;

import java.io.Serializable;

public class Participant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	
	private String content;

	public Participant(String title, int id, int id_pattern, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	
	
	
}
