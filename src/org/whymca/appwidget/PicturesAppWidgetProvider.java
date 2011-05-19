package org.whymca.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class PicturesAppWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}

	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_provider);

		views.setImageViewResource(R.id.img1, R.drawable.img1);
		views.setImageViewResource(R.id.img2, R.drawable.img2);

		views.setOnClickPendingIntent(R.id.img1, AppWidgetUtils.createPendingIntent(context, 1));
		views.setOnClickPendingIntent(R.id.img2, AppWidgetUtils.createPendingIntent(context, 2));

		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
}
