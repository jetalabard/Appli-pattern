package fr.dpapp.graphique;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.dpapp.metier.Pattern;
import fr.dpapp.modele.PatternDAO;

public class PatternListFragment extends ListFragment {
	private Callback callbackImpl;

	public interface Callback {
		void onPatternSelected(Pattern pattern);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Activity a = null;
		if (context instanceof Activity){
			a=(Activity) context;
			if (a instanceof Callback)
				callbackImpl = (Callback) a;
		}
		
	}

	@Override
	public void onDetach() {
		super.onDetach();
		callbackImpl = null;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		setListAdapter(new PatternAdapter(getActivity(),PatternDAO.getInstance(getActivity()).getPatterns()));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		return view;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (callbackImpl != null) {
			callbackImpl.onPatternSelected(((PatternAdapter) getListAdapter()).getItem(position));
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		((PatternAdapter) getListAdapter()).notifyDataSetChanged();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
}
