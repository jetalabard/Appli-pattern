package fr.dpapp.metier;

import java.io.Serializable;
import java.util.List;

public class KeyPoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	
	private List<String> content;

	public KeyPoint(int id, int id_pattern, String title, List<String> content) {
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
	public List<String> getContent() {
		return content;
	}

	
	
	
	
	
	
}
