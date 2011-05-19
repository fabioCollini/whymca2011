package org.whymca.appwidget.asset;

import org.whymca.appwidget.AppWidgetUtils;
import org.whymca.appwidget.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.net.Uri;
import android.widget.RemoteViews;

public class PicturesAppWidgetProviderAsset extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}

	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_provider_landscape_support);

		views.setImageViewUri(R.id.img1, createUri("img1"));
		views.setImageViewUri(R.id.img2, createUri("img2"));
		views.setImageViewUri(R.id.img3, createUri("img3"));
		views.setImageViewUri(R.id.img4, createUri("img4"));

		views.setOnClickPendingIntent(R.id.img1, AppWidgetUtils.createPendingIntent(context, 1));
		views.setOnClickPendingIntent(R.id.img2, AppWidgetUtils.createPendingIntent(context, 2));
		views.setOnClickPendingIntent(R.id.img3, AppWidgetUtils.createPendingIntent(context, 3));
		views.setOnClickPendingIntent(R.id.img4, AppWidgetUtils.createPendingIntent(context, 4));

		appWidgetManager.updateAppWidget(appWidgetId, views);
	}

	private static Uri createUri(String imageName) {
		return Uri.parse("content://org.whymca.appwidget.pictures/" + imageName);
	}
}
