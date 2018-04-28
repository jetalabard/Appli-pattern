package fr.dpapp.metier;

import java.io.Serializable;
import java.util.List;


public class Pattern implements Serializable,Comparable<Pattern>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private final int id;
	private Type type;
	private final String imagePattern;
	private final String title;
	private Description description;
	private List<Participant> participants;
	private List<KeyPoint> key_points;
	
	public Pattern(PatternBuilder builder)
	{
		this.id =builder.id;
		this.imagePattern = builder.imagePattern;
		this.title =builder.title;
		this.description = builder.description;
		this.participants = builder.participants;
		this.key_points = builder.key_points;
		this.type = builder.type;
	}
	
	public Pattern(int id, String image, String title) {
		this.id =id;
		this.imagePattern = image;
		this.title =title;
	}
	

	@Override
	public int compareTo(Pattern another) {
		return this.title.compareTo(another.title);
	}

	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Pattern)) {
	        return false;
	    }
		Pattern that = (Pattern) other;
	    return this.title==that.title;
	}
	
	@Override
	public int hashCode() {
	    return this.id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return title;
	}

	public boolean isFull()
	{
		if(description !=null 
				&& key_points!=null 
				&& participants!=null 
				&& title!=null 
				&& imagePattern != null
				&& type != null){
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @return the description
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * @return the participants
	 */
	public List<Participant> getParticipants() {
		return participants;
	}


	/**
	 * @return the key_points
	 */
	public List<KeyPoint> getKey_points() {
		return key_points;
	}


	/**
	 * @return the imagePattern
	 */
	public String getImagePattern() {
		return imagePattern;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}




	public static class PatternBuilder {
		
		private final int id;
		private final String imagePattern;
		private final String title;
		private Type type;
		private Description description;
		private List<Participant> participants;
		private List<KeyPoint> key_points;

		public PatternBuilder(int id,String imagePattern, String title) {
			this.id =id;
			this.imagePattern = imagePattern;
			this.title =title;
        }
		public PatternBuilder(Pattern p) {
			this.id =p.id;
			this.imagePattern = p.imagePattern;
			this.title =p.title;
        }
		
		 public PatternBuilder type(Type type) {
	            this.type = type;
	            return this;
	        }
	 
 
        public PatternBuilder description(Description description) {
            this.description = description;
            return this;
        }
 
        public PatternBuilder participants( List<Participant> participants) {
            this.participants = participants;
            return this;
        }
 
        public PatternBuilder key_points(List<KeyPoint> key_points) {
            this.key_points = key_points;
            return this;
        }
 
        public Pattern build() {
            return new Pattern(this);
        }
	}




	

	
	
	
	

}
