package fr.dpapp.metier;

import java.io.Serializable;

public class Type implements Serializable {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = "";
	  
	  public Type(String name){
		    this.name = name;
		  }
	   
	  public String getName()
	  {
		  return name;
	  }
	  @Override
	public String toString(){
	    return new String(name).substring(0, 2) + "("+name+")";
	  }
	 
}
