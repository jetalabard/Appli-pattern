package fr.dpapp.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;

public class ClickController implements View.OnClickListener{
	
	private String urlRedirectButton;
	private Activity activity;
	private AlertDialog alertDialog;
	
	public ClickController(Activity act) {
		this.activity = act;
	}
	public static ClickController newInstance(Activity act,String url)
	{
		ClickController c = new ClickController(act);
		c.setUrlRedirectButton(url);
		return c;
	}

	@Override
	public void onClick(View v) {
		testConnectionAndOpenNavigator(urlRedirectButton);
	}
	
	private void testConnectionAndOpenNavigator(final String urlData) {
		if(isNetworkAvailable())
		{
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+urlData));
			activity.startActivity(browserIntent);
		}
		else{
			  AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			  builder.setMessage("Connectez vous et rééssayez.");
			  builder.setTitle("Problème Connexion");
	             builder.setCancelable(true);
	              alertDialog  = builder.setPositiveButton("Rééssayer", new DialogInterface.OnClickListener() {

                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                            testConnectionAndOpenNavigator(urlData);
                         }
                     }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                        	 alertDialog.dismiss();
                         }
                     }).create();

             
             alertDialog.show();
		}
		
	}
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public String getUrlRedirectButton() {
		return urlRedirectButton;
	}

	public void setUrlRedirectButton(String urlRedirectButton) {
		this.urlRedirectButton = urlRedirectButton;
	}


}
