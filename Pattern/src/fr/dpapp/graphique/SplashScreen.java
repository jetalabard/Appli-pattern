package fr.dpapp.graphique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import fr.dpapp.controller.LoadingTask;
import fr.dpapp.controller.LoadingTask.LoadingTaskFinishedListener;

public class SplashScreen extends Activity implements LoadingTaskFinishedListener {

	private ProgressBar mProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		mProgress = (ProgressBar) findViewById(R.id.progressBar);
		mProgress.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(SplashScreen.this, "Chargement...", Toast.LENGTH_LONG).show();
			}
		});
		if(savedInstanceState == null){
			new LoadingTask(mProgress,this,this).execute();
		}

	}

	@Override
	public void onTaskFinished() {
		completeSplash();
	}
	private void completeSplash(){
		startApp();
		finish(); 
	}

	private void startApp() {
		Intent i = new Intent(SplashScreen.this, PatternListActivity.class);
		startActivity(i);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}

}