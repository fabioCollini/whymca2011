package org.whymca.appwidget.service;

import org.whymca.appwidget.AppWidgetUtils;
import org.whymca.appwidget.R;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.widget.RemoteViews;

public class PicturesAppWidgetService extends Service {

	@Override
	public void onStart(Intent intent, int startId) {
		int[] appWidgetIds = intent.getIntArrayExtra("appWidgetIds");
		for (int i = 0; i < appWidgetIds.length; i++) {
			updateAppWidget(this, appWidgetIds[i]);
		}
	}

	public static void updateAppWidget(Context context, int appWidgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_provider_landscape_support);

		views.setImageViewUri(R.id.img1, createUri("img1"));
		views.setImageViewUri(R.id.img2, createUri("img2"));
		views.setImageViewUri(R.id.img3, createUri("img3"));
		views.setImageViewUri(R.id.img4, createUri("img4"));

		views.setOnClickPendingIntent(R.id.img1, AppWidgetUtils.createPendingIntent(context, 1));
		views.setOnClickPendingIntent(R.id.img2, AppWidgetUtils.createPendingIntent(context, 2));
		views.setOnClickPendingIntent(R.id.img3, AppWidgetUtils.createPendingIntent(context, 3));
		views.setOnClickPendingIntent(R.id.img4, AppWidgetUtils.createPendingIntent(context, 4));

		AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, views);
	}

	private static Uri createUri(String imageName) {
		return Uri.parse("content://org.whymca.appwidget.pictures/" + imageName);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
