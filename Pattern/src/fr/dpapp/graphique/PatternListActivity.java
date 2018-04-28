package fr.dpapp.graphique;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import fr.dpapp.metier.Pattern;

public class PatternListActivity extends SimpleFragmentActivity implements PatternListFragment.Callback {
	@Override
	protected Fragment createFragment() { 
		return new PatternListFragment();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pattern, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.about)
		{
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			return true;
		}
		else{
			return super.onOptionsItemSelected(item);
		}
	}


	@Override
	public void onPatternSelected(Pattern pattern) {
		if (findViewById(R.id.detailFragmentContainer) == null) {
			Intent i = new Intent(this, PatternPagerActivity.class);
			i.putExtra(PatternFragment.EXTRA_PATTERN_ID, pattern.getId());
			startActivity(i);
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		} else {
			FragmentManager fm = getSupportFragmentManager();
			fm.beginTransaction()
			  .replace(R.id.detailFragmentContainer, PatternFragment.newInstance(pattern.getId()))
			  .commit();
		}
	}

}
