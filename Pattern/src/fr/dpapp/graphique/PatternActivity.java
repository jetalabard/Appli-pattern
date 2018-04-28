package fr.dpapp.graphique;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

public class PatternActivity extends SimpleFragmentActivity {

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pattern, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.about) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected Fragment createFragment() {
		if((Integer) getIntent().getSerializableExtra(PatternFragment.EXTRA_PATTERN_ID) == null)
		{
			return PatternFragment.newInstance(0);
		}
		else{
			return PatternFragment.newInstance(
					(Integer) getIntent().getSerializableExtra(PatternFragment.EXTRA_PATTERN_ID));
		}
	}
}
