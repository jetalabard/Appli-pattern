package fr.dpapp.modele;

import java.util.List;

import fr.dpapp.metier.Pattern;

public interface IPatternDAO {

	public List<Pattern> getPatterns();

	public Pattern getPattern(Integer id);
}
