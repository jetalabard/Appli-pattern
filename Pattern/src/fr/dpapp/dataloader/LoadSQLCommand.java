package fr.dpapp.dataloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import fr.dpapp.modele.RunException;

public class LoadSQLCommand{

	private Activity activity;

	public LoadSQLCommand(Activity act) {
		this.activity = act;
	}

	public List<String> execute(String file) {

		List<String> result = null;
		result = loadCommande(file);
		return result;
	}


	private List<String> loadCommande(String nameFile)
	{
		List<String> listeCommand = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader(activity.getAssets().open(nameFile), "ISO-8859-1"));

			String mLine;
			while ((mLine = reader.readLine()) != null) {
				listeCommand.add(mLine);
			}
		} catch (final IOException e) {
			new RunException(e,activity);

		} finally {
			if(reader != null){
				try {
					reader.close();
				} catch (final IOException e) {
					new RunException(e,activity);
				}
			}
		}

		return listeCommand;

	}


}
