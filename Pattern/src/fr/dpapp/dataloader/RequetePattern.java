package fr.dpapp.dataloader;

import java.util.List;

import fr.dpapp.metier.About;
import fr.dpapp.metier.Description;
import fr.dpapp.metier.KeyPoint;
import fr.dpapp.metier.Participant;
import fr.dpapp.metier.Pattern;
import fr.dpapp.metier.Type;


public interface RequetePattern {

	Description loadDescription(int idDescription);
	
	List<Participant> loadParticipants(List<String> idParticipants);
	
	Participant loadParticipant(int idParticipant);
	
	List<Pattern> loadPatterns();
	
	Pattern loadPattern(int idPattern);

	List<Participant> loadParticipantsByIdPattern(int id_pattern);

	List<Description> loadDescriptions();

	Description loadDescriptionByIdPattern(int id_pattern);

	List<KeyPoint> loadKeyPoints();

	List<KeyPoint> loadKeyPointsByIdPattern(int id_pattern);
	
	Type loadType(int id_pattern);

	About loadAbout();
	
}
