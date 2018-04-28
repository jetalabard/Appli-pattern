package fr.dpapp.metier;

import java.io.Serializable;
import java.util.List;

public class Description implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private Type type;
	
	private String intent;
	
	private List<Applicability> ListeApplicabilités;

	
	public Description(int id, Type type, String intent, int id_pattern,List<Applicability> ListeApplicabilités) {
		this.id = id;
		this.type = type;
		this.intent = intent;
		this.setId_pattern(id_pattern);
		this.ListeApplicabilités = ListeApplicabilités;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}


	/**
	 * @return the intent
	 */
	public String getIntent() {
		return intent;
	}


	/**
	 * @return the listeApplicabilités
	 */
	public List<Applicability> getListeApplicabilités() {
		return ListeApplicabilités;
	}




	public void setId_pattern(int id_pattern) {
	}





}
