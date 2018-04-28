package fr.dpapp.donnees.test;

import java.util.ArrayList;
import java.util.List;

import fr.dpapp.metier.Applicability;
import fr.dpapp.metier.Description;
import fr.dpapp.metier.KeyPoint;
import fr.dpapp.metier.Participant;
import fr.dpapp.metier.Pattern;
import fr.dpapp.metier.Type;
import fr.dpapp.modele.XMLTags;

public class Patterns {

	private List<Pattern> patterns ;
	
	public Patterns() {
		patterns = new ArrayList<Pattern>();
		ArrayList<Participant> listeParticipants = new ArrayList<Participant>();
		listeParticipants.add(new Participant("But",1,1,"D�finit une interface sp�cifique du domaine qu'utilise le client."));
		listeParticipants.add(new Participant("Client",2,1,"Collabore avec les objets en se conformant � l'interface du But."));
		listeParticipants.add(new Participant("Adapt�",3,1,"D�finit une interface existante qui demande adaptation."));
		listeParticipants.add(new Participant("Adaptateur",4,1,"Adapte l'interface de l'adapt� � l'interface But."));
		
		
		ArrayList<Applicability> listeApplicabilities = new ArrayList<Applicability>();
		listeApplicabilities.add(new Applicability("Vous voulez utiliser une classe existante, mais dont l'interface ne co�ncide pas avec celle escompt�e."));
		listeApplicabilities.add(new Applicability("Vous souhaitez cr�er une classe r�utilisable qui collabore avec des classes sans relations avec elle et encore inconnues, c'est-�-dire avec des classes qui n'auront pas n�cessairement des interfaces compatibles."));
		listeApplicabilities.add(new Applicability("(pour le cas adaptateur d'objet seulement) vous avez besoin d'utiliser plusieurs sous-classes existantes, mais l'adaptation de leur interface par d�rivation de chacune d'entre elles est impraticable. Un adaptateur objet peut adapter l'interface de sa classe parente."));
		
		Description description = 
				new Description(0,new Type("Structurel"),"Convertit l'interface d'une classe en une autre conform�ment � l'attente du client. L'Adaptateur permet � des classes de collaborer, alors qu'elles n'auraient pas pu le faire du fait d'interfaces incompatibles.",1,listeApplicabilities);
		
		ArrayList<String> listeKey = new ArrayList<String>();
		listeKey.add("Factorisation du cha�nage entre objets");
		listeKey.add("Point d'acc�s unique pour le client");
		listeKey.add("L'ajout ou la suppression d'un nouveau �l�ment dans la cha�ne ne n�cessite pas de modification de code");
		
		
		ArrayList<KeyPoint> listeKeyPoints = new ArrayList<KeyPoint>();
		listeKeyPoints.add(new KeyPoint(1,1,"Extensibilit�",listeKey));
		
		patterns.add(
				new Pattern.PatternBuilder(0,"adapter.png","Adaptateur")
				.description(description)
				.participants(listeParticipants)
				.key_points(listeKeyPoints)
				.type(new Type(XMLTags.STRUCTUREL))
				.build()
				);
		patterns.add(
				new Pattern.PatternBuilder(1,"chaine_responsabilite.png","Chaine de responsabilit�")
				.description(description)
				.participants(listeParticipants)
				.key_points(listeKeyPoints)
				.type(new Type(XMLTags.COMPORTEMENTAL))
				.build()
				);
		patterns.add(
				new Pattern.PatternBuilder(2,"singleton.png","Singleton")
				.description(description)
				.participants(listeParticipants)
				.key_points(listeKeyPoints)
				.type(new Type(XMLTags.COMPORTEMENTAL))
				.build()
				);
	}

	/**
	 * @return the patterns
	 */
	public List<Pattern> getPatterns() {
		return patterns;
	}
	
	/**
	 * @return the patterns
	 */
	public Pattern getPattern(int id) {
		return patterns.get(id);
	}

	/**
	 * @param patterns the patterns to set
	 */
	public void setPatterns(List<Pattern> patterns) {
		this.patterns = patterns;
	}
	
	
}
