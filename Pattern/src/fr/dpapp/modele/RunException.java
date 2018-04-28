package fr.dpapp.modele;

import android.app.Activity;
import android.content.Intent;
import fr.dpapp.graphique.ExceptionActivity;
import fr.dpapp.graphique.ExceptionFragment;

public class RunException {

	public RunException(final Exception exception,final Activity act) {
		exception.printStackTrace();
		act.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				Intent i = new Intent(act, ExceptionActivity.class);
				i.putExtra(ExceptionFragment.EXCEPTION,String.valueOf(exception.getMessage()));
				act.startActivity(i);				
			}
		});
		
	}
}
