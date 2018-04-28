package fr.dpapp.buildImage;

import android.app.Activity;
import android.widget.TextView;
import fr.dpapp.graphique.R;

public class ImageStructural extends ImageClassificationPattern {

	@Override
	public TextView draw(Activity act,TextView patternImageView) {
		patternImageView.setBackgroundResource(R.drawable.greencircle);
		patternImageView.setText("St");
		return patternImageView;
	}

}
