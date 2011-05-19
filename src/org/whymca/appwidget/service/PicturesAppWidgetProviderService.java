package org.whymca.appwidget.service;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class PicturesAppWidgetProviderService extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Intent intent = new Intent(context, PicturesAppWidgetService.class);
		intent.putExtra("appWidgetIds", appWidgetIds);
		context.startService(intent);
	}

}
