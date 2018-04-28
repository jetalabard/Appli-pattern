package fr.dpapp.dataloader;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.dpapp.metier.About;
import fr.dpapp.metier.Applicability;
import fr.dpapp.metier.Description;
import fr.dpapp.metier.KeyPoint;
import fr.dpapp.metier.Participant;
import fr.dpapp.metier.Pattern;
import fr.dpapp.metier.Type;
import fr.dpapp.modele.RunException;

public class AccessData implements RequetePattern{

	private SQLiteDatabase dataBase;
	
	private static AccessData instance;

	protected static final String TAG = "DataAdapter";
	
	private Activity act;

	private AccessData(Activity context) {
		dataBase = DataBase.getInstance(context).getDB();
		this.act = context;
	}
	
	public static AccessData getInstance(Activity context)
	{
		if(instance == null)
			instance = new AccessData(context);
		return instance;
	}

	@Override
	public List<Description> loadDescriptions() {
		List<Description> descriptions = null;
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM Description";
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			descriptions = new ArrayList<Description>();
			try {
				do {
					List<Applicability> applicabilities = 
							showApplicabilitiesByIdDescrition(cursor.getInt(cursor.getColumnIndex("ID")));
					Description desc = new Description(
							cursor.getInt(cursor.getColumnIndex("ID")),
							new Type(cursor.getString(cursor.getColumnIndex("TYPE"))),
							cursor.getString(cursor.getColumnIndex("INTENT")),
							cursor.getInt(cursor.getColumnIndex("ID_PATTERN")),
							applicabilities);

					descriptions.add(desc);
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}

		return descriptions;
	}
	
	@Override
	public Description loadDescription(int id_decription) {
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM Description where ID=?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id_decription)});
		Description desc = null;
		if (cursor.moveToFirst()) {
			try {
				do {
					List<Applicability> applicabilities = 
							showApplicabilitiesByIdDescrition(cursor.getInt(cursor.getColumnIndex("ID")));
					 desc = new Description(
							cursor.getInt(cursor.getColumnIndex("ID")),
							new Type(cursor.getString(cursor.getColumnIndex("TYPE"))),
							cursor.getString(cursor.getColumnIndex("INTENT")),
							cursor.getInt(cursor.getColumnIndex("ID_PATTERN")),
							applicabilities);

				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}
		return desc;
	}
		
		@Override
		public About loadAbout() {
			SQLiteDatabase db = dataBase;
			String query = "SELECT * FROM About";
			Cursor cursor = db.rawQuery(query, null);
			About about = null;
			if (cursor.moveToFirst()) {
				try {
					do {
						about = new About(
								cursor.getString(cursor.getColumnIndex("IDEA")),
								cursor.getString(cursor.getColumnIndex("DATA")),
								cursor.getString(cursor.getColumnIndex("URL_DATA")),
								cursor.getString(cursor.getColumnIndex("URL_FACEBOOK")),
								cursor.getString(cursor.getColumnIndex("MESS_SITE")),
								cursor.getString(cursor.getColumnIndex("URL_SITE")));
					} while (cursor.moveToNext()) ;
				} 
				catch (Exception e) {
					new RunException(e,act);
				}
				finally {
					cursor.close();
				}
			}

		return about;
	}
	
	@Override
	public Description loadDescriptionByIdPattern(int id_pattern) {
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM Description where ID_PATTERN=?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id_pattern)});
		Description desc = null;
		if (cursor.moveToFirst()) {
			try {
					List<Applicability> applicabilities = 
							showApplicabilitiesByIdDescrition(cursor.getInt(cursor.getColumnIndex("ID")));
					 desc = new Description(
							cursor.getInt(cursor.getColumnIndex("ID")),
							new Type(cursor.getString(cursor.getColumnIndex("TYPE"))),
							cursor.getString(cursor.getColumnIndex("INTENT")),
							cursor.getInt(cursor.getColumnIndex("ID_PATTERN")),
							applicabilities);
			} 
			catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}

		return desc;
	}

	private List<Applicability> showApplicabilitiesByIdDescrition(int id_description) {
		List<Applicability> applicabilities = new ArrayList<Applicability>();
		SQLiteDatabase db = dataBase;
		String query = "SELECT CONTENT FROM Applicability where ID_DESCRIPTION=?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id_description)});
		if (cursor.moveToFirst()) {
			applicabilities = new ArrayList<Applicability>();
			try {
				do {
					Applicability app = new Applicability(cursor.getString(cursor.getColumnIndex("CONTENT")));
					applicabilities.add(app);
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}
		return applicabilities;
	}


	


	@Override
	public List<Pattern> loadPatterns() {
		List<Pattern> patterns = null;
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM Pattern";
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			patterns = new ArrayList<Pattern>();
			try {
				do {

					patterns.add(new
							Pattern.PatternBuilder(
									cursor.getInt(cursor.getColumnIndex("ID")),
									cursor.getString(cursor.getColumnIndex("IMAGE")),
									cursor.getString(cursor.getColumnIndex("TITLE"))
									).type(
											loadType(
													cursor.getInt(cursor.getColumnIndex("ID"))))
							.build());
					
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}
		return patterns;
	}
	@Override
	public Type loadType(int id_pattern) {
		SQLiteDatabase db = dataBase;
		String query = "SELECT TYPE FROM Description where ID_PATTERN=?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id_pattern)});
		Type type = null;
		if (cursor.moveToFirst()) {
			try{
				type = new Type(cursor.getString(cursor.getColumnIndex("TYPE")));
			}
			catch (Exception e) {
				new RunException(e,act);
			}
			finally{
				cursor.close();
			}
		}
		return type;
	}

	@Override
	public Pattern loadPattern(int idPattern) {
		Pattern pat = null;
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM Pattern where ID=?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idPattern)});
		if (cursor.moveToFirst()) {
			try {
				if (cursor.moveToFirst()) {
					pat = new
							Pattern.PatternBuilder(
									idPattern,
									cursor.getString(cursor.getColumnIndex("IMAGE")),
									cursor.getString(cursor.getColumnIndex("TITLE"))
									).build();
				}
			}catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}
		return pat;
	}


	@Override
	public List<Participant> loadParticipants(List<String> id_description) {
		List<Participant> participants = null;
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM Participant";
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			participants = new ArrayList<Participant>();
			try {
				do {
					Participant pat = new Participant(
							cursor.getString(cursor.getColumnIndex("TITLE")),
							cursor.getInt(cursor.getColumnIndex("ID")),
							cursor.getInt(cursor.getColumnIndex("ID_PATTERN")),
							cursor.getString(cursor.getColumnIndex("CONTENT"))
							);

					participants.add(pat);
				} while (cursor.moveToNext()) ;
			}
			catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}
		return participants;
	}

	@Override
	public Participant loadParticipant(int id_participant) {
		Participant pat = null;
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM Participant where ID=?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id_participant)});
		if (cursor.moveToFirst()) {
			try {
				do {
					pat = new Participant(
							cursor.getString(cursor.getColumnIndex("TITLE")),
							cursor.getInt(cursor.getColumnIndex("ID")),
							cursor.getInt(cursor.getColumnIndex("ID_PATTERN")),
							cursor.getString(cursor.getColumnIndex("CONTENT"))
							);

				} while (cursor.moveToNext()) ;
			}
			catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}
		return pat;
	}

	@Override
	public List<Participant> loadParticipantsByIdPattern(int id_pattern) {
		List<Participant> participants = null;
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM Participant where ID_PATTERN=?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id_pattern)});
		if (cursor.moveToFirst()) {
			participants = new ArrayList<Participant>();
			try {
				do {
					Participant pat = new Participant(
							cursor.getString(cursor.getColumnIndex("TITLE")),
							cursor.getInt(cursor.getColumnIndex("ID")),
							id_pattern,
							cursor.getString(cursor.getColumnIndex("CONTENT"))
							);

					participants.add(pat);
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}
		return participants;
	}
	
	@Override
	public List<KeyPoint> loadKeyPoints() {
		List<KeyPoint> keyPoints = null;
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM Key_Point";
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			keyPoints = new ArrayList<KeyPoint>();
			try {
				do {
						KeyPoint key = new KeyPoint(cursor.getInt(cursor.getColumnIndex("ID")), 
								cursor.getInt(cursor.getColumnIndex("ID_PATTERN")),
								cursor.getString(cursor.getColumnIndex("TITLE")), 
								showContentKeyPointByIdKeyPoint(cursor.getInt(cursor.getColumnIndex("ID"))));
					keyPoints.add(key);
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}

		return keyPoints;
	}

	private List<String> showContentKeyPointByIdKeyPoint(int id_key) 
	{
		List<String> content = null;
		SQLiteDatabase db = dataBase;
		String query = "SELECT VALUE FROM Content_Key_Point where ID_KEY_POINT=?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id_key)});
		if (cursor.moveToFirst()) {
			content = new ArrayList<String>();
			try {
				do {
					content.add(cursor.getString(cursor.getColumnIndex("VALUE")));
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}

		return content;
	}
	
	
	@Override
	public List<KeyPoint> loadKeyPointsByIdPattern(int id_pattern) {
		List<KeyPoint> keyPoints = null;
		SQLiteDatabase db = dataBase;
		String query = "SELECT * FROM Key_Point where ID_PATTERN=?";
		Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id_pattern)});
		if (cursor.moveToFirst()) {
			keyPoints = new ArrayList<KeyPoint>();
			try {
				do {
						KeyPoint key = new KeyPoint(cursor.getInt(cursor.getColumnIndex("ID")), 
								id_pattern,
								cursor.getString(cursor.getColumnIndex("TITLE")), 
								showContentKeyPointByIdKeyPoint(cursor.getInt(cursor.getColumnIndex("ID"))));
					keyPoints.add(key);
				} while (cursor.moveToNext()) ;
			} 
			catch (Exception e) {
				new RunException(e,act);
			}
			finally {
				cursor.close();
			}
		}

		return keyPoints;
	}
	
	

}
