package fr.dpapp.graphique;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fr.dpapp.metier.Applicability;
import fr.dpapp.metier.KeyPoint;
import fr.dpapp.metier.Participant;
import fr.dpapp.metier.Pattern;
import fr.dpapp.modele.PatternDAO;
import fr.dpapp.modele.RunException;
import pl.polidea.view.ZoomView;

public class PatternFragment extends Fragment {
	private static final String PATTERN = "pattern";
	public static final String EXTRA_PATTERN_ID = "fr.dpapp.pattern_id";
	private Pattern pattern;
	private View rootView;
	private ZoomView zoomView;

	public static PatternFragment newInstance(int id_pattern) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_PATTERN_ID, id_pattern);
		PatternFragment fragment = new PatternFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

		if (savedInstanceState != null) {
			pattern = (Pattern) savedInstanceState.getSerializable(PATTERN);
		} else {
			pattern = PatternDAO.getInstance(getActivity()).getPattern((Integer)getArguments().getSerializable(EXTRA_PATTERN_ID));
		}
	}



	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable(PATTERN, pattern);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_pattern, container, false);
		
		fillDescriptionPattern();
		if(pattern.getKey_points()!= null && !pattern.getKey_points().isEmpty() ){
			fillKeyPointPattern();
		}
		if(pattern.getParticipants()!= null && !pattern.getParticipants().isEmpty() ){
			fillParticipantPattern();
		}
		View v = inflater.inflate(R.layout.zoomableimageview, container, false);
		v = fillImagePattern(v);
		zoomView = new ZoomView(getActivity());
		zoomView.addView(v);

		RelativeLayout layoutImage = (RelativeLayout) rootView.findViewById(R.id.layoutImage);
		layoutImage.addView(zoomView);

		return rootView;
	}

	private void fillParticipantPattern() {
		String participant = "";
		for(Participant p : pattern.getParticipants())
		{
			if(pattern.getParticipants().indexOf(p)== pattern.getParticipants().size()-1){
				participant += p.getTitle()+" : \n\n    " + p.getContent()+ "\n";
			}
			else{participant += p.getTitle()+" : \n\n    " + p.getContent()+ "\n\n";}
			
		}
		TextView participants = (TextView) rootView.findViewById(R.id.participants);
		participants.setText(participant);
		TextView intituleParticipants = (TextView) rootView.findViewById(R.id.intitule4);
		intituleParticipants.setVisibility(View.VISIBLE);
		participants.setVisibility(View.VISIBLE);
	}

	private void fillKeyPointPattern() {
		String keypoints = "";
		int i=1;
		for(KeyPoint k : pattern.getKey_points())
		{
			keypoints +=i+". "+ k.getTitle()+" : \n\n";
			int l=1;
			for(String st : k.getContent())
			{
				if(k.getContent().indexOf(st)== k.getContent().size()-1 
						&& pattern.getKey_points().indexOf(k)== pattern.getKey_points().size()-1 ){
					keypoints+= "    "+i+"."+l+". "+st;
				}
				else{keypoints+= "    "+i+"."+l+". "+st+"\n\n";}
				l++;
			}
			i++;
		}
		TextView keypoint = (TextView) rootView.findViewById(R.id.keypoint);
		keypoint.setText(keypoints);
		TextView intituleKeyPoint = (TextView) rootView.findViewById(R.id.intitule3);
		intituleKeyPoint.setVisibility(View.VISIBLE);
		keypoint.setVisibility(View.VISIBLE);

	}

	private void fillDescriptionPattern() {
		TextView type = (TextView) rootView.findViewById(R.id.type);
		type.setText("Type : " + pattern.getType().toString());

		TextView intent = (TextView) rootView.findViewById(R.id.intent);
		intent.setText("Intention : \n\n    " + pattern.getDescription().getIntent());

		TextView applicability = (TextView) rootView.findViewById(R.id.applicability);
		String s = "\n";
		for(Applicability a : pattern.getDescription().getListeApplicabilités())
		{
			if(pattern.getDescription().getListeApplicabilités().indexOf(a)== pattern.getDescription().getListeApplicabilités().size()-1){
				s += "    - " +a.getValue();
			}
			else{s += "    - " +a.getValue()+"\n\n";}
			
		}

		applicability.setText("Applicabilité : \n" + s);
	}

	private View fillImagePattern(View v) {

		ImageView imagePattern = (ImageView) v.findViewById(R.id.imageViewPattern);
		AssetManager manager = getActivity().getAssets();
		InputStream open;
		Bitmap bitmap = null;
		try {
			open = manager.open("image/"+pattern.getImagePattern());
			bitmap = BitmapFactory.decodeStream(open);
		} catch (IOException e) {
			new RunException(e,getActivity());
		}
		imagePattern.setImageBitmap(bitmap);
		return v;
	}

}