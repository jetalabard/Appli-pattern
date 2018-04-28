package fr.dpapp.graphique;

import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class ExceptionActivity extends SimpleFragmentActivity{

	@Override
	protected Fragment createFragment() {
		return ExceptionFragment.newInstance(
				(String) getIntent().getSerializableExtra(ExceptionFragment.EXCEPTION));
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(this) != null) {
				NavUtils.navigateUpFromSameTask(this);
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
