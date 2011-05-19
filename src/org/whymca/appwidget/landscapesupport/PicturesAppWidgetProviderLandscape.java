package org.whymca.appwidget.landscapesupport;

import org.whymca.appwidget.AppWidgetUtils;
import org.whymca.appwidget.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class PicturesAppWidgetProviderLandscape extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}

	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_provider_landscape_support);

		views.setImageViewResource(R.id.img1, R.drawable.img1);
		views.setImageViewResource(R.id.img2, R.drawable.img2);
		views.setImageViewResource(R.id.img3, R.drawable.img3);
		views.setImageViewResource(R.id.img4, R.drawable.img4);

		views.setOnClickPendingIntent(R.id.img1, AppWidgetUtils.createPendingIntent(context, 1));
		views.setOnClickPendingIntent(R.id.img2, AppWidgetUtils.createPendingIntent(context, 2));
		views.setOnClickPendingIntent(R.id.img3, AppWidgetUtils.createPendingIntent(context, 3));
		views.setOnClickPendingIntent(R.id.img4, AppWidgetUtils.createPendingIntent(context, 4));

		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
}
