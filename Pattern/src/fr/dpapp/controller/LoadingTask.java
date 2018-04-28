package fr.dpapp.controller;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.widget.ProgressBar;
import fr.dpapp.dataloader.DataBase;

public class LoadingTask extends AsyncTask<String, Integer, Integer> {

	public interface LoadingTaskFinishedListener {
		void onTaskFinished(); // If you want to pass something back to the listener add a param to this method
	}

	private final ProgressBar progressBar;
	private final LoadingTaskFinishedListener finishedListener;
	private Activity activity;
	private WakeLock mWakeLock;

	/**
	 * A Loading task that will load some resources that are necessary for the app to start
	 * @param progressBar - the progress bar you want to update while the task is in progress
	 * @param finishedListener - the listener that will be told when this task is finished
	 */
	public LoadingTask(ProgressBar progressBar, LoadingTaskFinishedListener finishedListener,Activity act) {
		this.progressBar = progressBar;
		this.finishedListener = finishedListener;
		this.activity = act;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		//permet de continuer la tache asynchrone même en veille
		PowerManager pm = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
				getClass().getName());
		mWakeLock.acquire();
	}

	@Override
	protected Integer doInBackground(String... params) {
		if(checkDataBase()==false){
			this.progressBar.setVisibility(View.VISIBLE);
			downloadResources();
		}
		else{
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		return 1234;
	}

	private boolean checkDataBase() {
		File database=activity.getApplicationContext().getDatabasePath(DataBase.DATABASE_NAME);

		if (!database.exists()) {
			return false;
		} else {
			return true;
		}
	}


	private void downloadResources() {
		int count = 10;
		for (int i = 0; i < count; i++) {

			int progress = (int) ((i / (float) count) * 100);
			publishProgress(progress);
			if(i==0){
				DataBase.getInstance(activity);
			}
		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		progressBar.setProgress(values[0]); 
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		if(mWakeLock != null){
			mWakeLock.release();
		}
		finishedListener.onTaskFinished(); 
	}
}