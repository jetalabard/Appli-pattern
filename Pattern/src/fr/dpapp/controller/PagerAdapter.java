package fr.dpapp.controller;

import java.util.List;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import fr.dpapp.graphique.PatternFragment;
import fr.dpapp.metier.Pattern;
import fr.dpapp.modele.PatternDAO;

public class PagerAdapter extends FragmentStatePagerAdapter
implements OnPageChangeListener {

	public PagerAdapter(FragmentManager fm,List<Pattern> patterns,Activity act) {
		super(fm);
		this.activity = act;
		this.patterns = patterns;
	}

	private Activity activity;
	private List<Pattern> patterns;
	

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		Pattern pattern = patterns.get(arg0);
		if (pattern.getTitle() != null) {
			activity.setTitle(pattern.getTitle());
		}
		
	}
	
	@Override
	public Fragment getItem(int pos) {
		Fragment frag =  PatternFragment.newInstance(
				PatternDAO.getInstance(activity.getParent()).getPattern(patterns.get(pos).getId())
				.getId());
		return frag;
	}

	

	@Override
	public int getCount() {
		return patterns.size();
	}

}
