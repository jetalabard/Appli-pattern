package fr.dpapp.buildImage;

import fr.dpapp.metier.Type;
import fr.dpapp.modele.XMLTags;

public class ImageClassificationPatternFactory {

	public ImageClassificationPattern getImageClassificationPattern(Type type){
		String typeName = type.getName();
		if(typeName.equals(XMLTags.CREATEUR)){
			return new ImageCreator();

		} else if(typeName.equals(XMLTags.STRUCTUREL)){
			return new ImageStructural();

		} else if(typeName.equals(XMLTags.COMPORTEMENTAL)){
			return new ImageComportemental();
		}
		return new ImageComportemental();
	}
}
