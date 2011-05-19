package org.whymca.appwidget;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class PictureViewer extends Activity {

	public static final String IMAGE = "image";

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.picture_detail);
		ImageView image = (ImageView) findViewById(R.id.image);
		String assetFile = getIntent().getStringExtra(IMAGE);

		try {
			Bitmap bitmap = BitmapFactory.decodeStream(getAssets().open(assetFile + "_full.JPG"));
			image.setImageBitmap(bitmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
