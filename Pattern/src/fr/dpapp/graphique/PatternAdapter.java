package fr.dpapp.graphique;


import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.dpapp.buildImage.ImageClassificationPatternFactory;
import fr.dpapp.metier.Pattern;


public class PatternAdapter extends ArrayAdapter<Pattern> {
	
	private Activity act;
	
	public PatternAdapter(Activity context, List<Pattern> list)
    {
        super(context,0, list);
        this.act = context;

    }
	private static class ViewHolder {
		TextView patternTitleView;
		TextView patternImageView;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder vh;

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item2, parent,false);
			vh = new ViewHolder();
			vh.patternTitleView = (TextView) convertView.findViewById(R.id.title);
			vh.patternImageView = (TextView) convertView.findViewById(R.id.image);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		Pattern pattern = getItem(position);
		if (pattern != null) {
			vh.patternTitleView.setText(pattern.getTitle());
			if(pattern.getType() != null){
				vh.patternImageView = new ImageClassificationPatternFactory().
						getImageClassificationPattern(pattern.getType()).draw(act,vh.patternImageView);
			}
		}
		
		return convertView;
	}
}
