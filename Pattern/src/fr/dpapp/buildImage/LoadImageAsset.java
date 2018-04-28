package fr.dpapp.buildImage;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import fr.dpapp.modele.RunException;

public class LoadImageAsset {
	
	private Activity act ;
	
	public LoadImageAsset(Activity act) {
		this.act = act;
	}

	public Bitmap getImage(String imagePath)
    {
        AssetManager assetManager = act.getAssets();
        InputStream istr = null;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open("image/"+imagePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
        	new RunException(e,act);
        }
        return bitmap;
    }
}
