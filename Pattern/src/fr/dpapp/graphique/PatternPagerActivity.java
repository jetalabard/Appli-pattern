package fr.dpapp.graphique;

import java.util.List;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import fr.dpapp.controller.PagerAdapter;
import fr.dpapp.controller.ZoomOutPageTransformer;
import fr.dpapp.metier.Pattern;
import fr.dpapp.modele.PatternDAO;

public class PatternPagerActivity extends FragmentActivity {
	private ViewPager viewPager;
	private List<Pattern> patterns = PatternDAO.getInstance(this).getPatterns();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ColorDrawable cd = new ColorDrawable(0xFF33B5E5);
		getActionBar().setBackgroundDrawable(cd);
		
		viewPager = new ViewPager(this);
		viewPager.setId(R.id.viewPager);
		setContentView(viewPager);

		FragmentManager fragmentManager = getSupportFragmentManager();
		viewPager.setAdapter(new PagerAdapter(fragmentManager,patterns,this));
		viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		viewPager.addOnPageChangeListener(new PagerAdapter(fragmentManager,patterns,this));

		int currentPatternId = (Integer) getIntent().getSerializableExtra(PatternFragment.EXTRA_PATTERN_ID);
		Pattern currentPattern = PatternDAO.getInstance(this).getPattern(currentPatternId);
		int posCrime = patterns.indexOf(currentPattern);
		viewPager.setCurrentItem(posCrime);
		if (currentPattern.getTitle() != null) {
			setTitle(currentPattern.getTitle());
		}
		
	        	
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
