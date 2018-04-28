package fr.dpapp.graphique;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExceptionFragment extends Fragment{

	public static final String EXCEPTION = "fr.dpapp.exception";
	
	private String exception;
	
	public ExceptionFragment(String ex) {
		this.exception = ex;
	}


	public static ExceptionFragment newInstance(String ex) {
		Bundle args = new Bundle();
		args.putSerializable(EXCEPTION, ex);
		ExceptionFragment fragment = new ExceptionFragment(ex);
		fragment.setArguments(args);
		return fragment;
	}
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		getActivity().setTitle(R.string.exception);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_exeception, container, false);
		
		TextView textexception = (TextView) rootView.findViewById(R.id.textException);
		textexception.setText(exception);
		
		return rootView;
	}
}
