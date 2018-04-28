package fr.dpapp.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import fr.dpapp.dataloader.AccessData;
import fr.dpapp.metier.Pattern;

public class PatternDAO implements IPatternDAO {
	
	private Activity act;
	
	private static PatternDAO instance =null;
	
	private List<Pattern> patterns;
	
	private PatternDAO(Activity context) {
		this.act = context;
		patterns = new ArrayList<Pattern>();
	}
	
	public static PatternDAO getInstance(Activity context)
	{
		if(instance == null)
			instance = new PatternDAO(context);
		return instance;
	}
	/**
	 * map permettant de stocké la valeur des patterns 
	 * et de ne pas aller les chercher en base s'ils sont déjà dans la liste
	 */
	

	/**
	 * retourne un pattern composé de tous ses attributs
	 */
	@Override
	public Pattern getPattern(Integer id) {
		
		try{
			AccessData acces = AccessData.getInstance(act);
			for(Pattern p : patterns)
			{
				if(p.getId().equals(id))
				{
					if(!p.isFull())
					{
						Pattern addPattern = getPattern(p,id,acces);
						patterns.remove(p);
						patterns.add(addPattern);
						Collections.sort(patterns);
						return addPattern;
					}
					return p;
				}
			}
			
		}catch(Exception e)
		{
			new RunException(e,act);
		}
		return null;
	}
	
	private Pattern getPattern(Pattern p,int id,AccessData acces)
	{
		try{
			return new
					Pattern.PatternBuilder(p)
					.description(acces.loadDescriptionByIdPattern(id))
					.participants(acces.loadParticipantsByIdPattern(id))
					.key_points(acces.loadKeyPointsByIdPattern(id))
					.type(acces.loadType(id))
					.build();
		}catch(Exception e)
		{
			new RunException(e,act);
			return null;
		}
		
		
	}

	/**
	 * retourne la liste des patterns constitué uniquement du titre,image et id
	 */
	@Override
	public List<Pattern> getPatterns() {
		if(patterns.isEmpty())
		{
			try{
				patterns.addAll(AccessData.getInstance(act).loadPatterns());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			Collections.sort(patterns);
		}
		return patterns;
	}
	
	

}
