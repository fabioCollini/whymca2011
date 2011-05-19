package org.whymca.appwidget.config;

import org.whymca.appwidget.PrefsSaver;
import org.whymca.appwidget.R;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class PicturesAppWidgetConfigure extends Activity {

	protected int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	private CheckBox showBackgroundCheck;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setResult(RESULT_CANCELED);
		setContentView(R.layout.appwidget_configure);

		showBackgroundCheck = (CheckBox) findViewById(R.id.show_background);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}

		if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
			finish();
		}

		showBackgroundCheck.setChecked(PrefsSaver.loadShowBackground(this, mAppWidgetId));
	}

	public void save(View v) {
		boolean checked = showBackgroundCheck.isChecked();
		PrefsSaver.saveShowBackground(this, mAppWidgetId, checked);

		PicturesAppWidgetProviderConfig.updateAppWidget(this, AppWidgetManager.getInstance(this), mAppWidgetId, checked);

		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		setResult(RESULT_OK, resultValue);
		finish();
	}

	protected void updateAppWidget(boolean checked, AppWidgetManager appWidgetManager) {
		PicturesAppWidgetProviderConfig.updateAppWidget(this, appWidgetManager, mAppWidgetId, checked);
	}

}
