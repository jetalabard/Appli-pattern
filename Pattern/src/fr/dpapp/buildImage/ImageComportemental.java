package fr.dpapp.buildImage;

import android.app.Activity;
import android.widget.TextView;
import fr.dpapp.graphique.R;

public class ImageComportemental extends ImageClassificationPattern{

	@Override
	public TextView draw(Activity act,TextView patternImageView) {
		patternImageView.setBackgroundResource(R.drawable.bluecircle);
		patternImageView.setText("Co");
		return patternImageView;
	}

}
