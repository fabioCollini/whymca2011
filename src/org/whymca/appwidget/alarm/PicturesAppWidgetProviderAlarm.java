package org.whymca.appwidget.alarm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.whymca.appwidget.AppWidgetUtils;
import org.whymca.appwidget.R;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.widget.RemoteViews;

public class PicturesAppWidgetProviderAlarm extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		AlarmManager alarms = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];
			int updateRate = (new Random().nextInt(7) + 3) * 1000;
			PendingIntent intent = createPendingIntent(context, appWidgetId);
			alarms.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), updateRate, intent);
		}
	}

	public PendingIntent createPendingIntent(Context context, int appWidgetId) {
		Intent widgetUpdate = new Intent(context, AlarmReceiver.class);
		widgetUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetId);
		PendingIntent newPending = PendingIntent.getBroadcast(context, appWidgetId, widgetUpdate, PendingIntent.FLAG_UPDATE_CURRENT);
		return newPending;
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		for (int appWidgetId : appWidgetIds) {
			AlarmManager alarms = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
			alarms.cancel(createPendingIntent(context, appWidgetId));
		}
		super.onDeleted(context, appWidgetIds);
	}

	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_provider_landscape_support);

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
		Collections.shuffle(list);

		views.setImageViewUri(R.id.img1, createUri("img" + list.get(0)));
		views.setImageViewUri(R.id.img2, createUri("img" + list.get(1)));
		views.setImageViewUri(R.id.img3, createUri("img" + list.get(2)));
		views.setImageViewUri(R.id.img4, createUri("img" + list.get(3)));

		views.setOnClickPendingIntent(R.id.img1, AppWidgetUtils.createPendingIntent(context, list.get(0)));
		views.setOnClickPendingIntent(R.id.img2, AppWidgetUtils.createPendingIntent(context, list.get(1)));
		views.setOnClickPendingIntent(R.id.img3, AppWidgetUtils.createPendingIntent(context, list.get(2)));
		views.setOnClickPendingIntent(R.id.img4, AppWidgetUtils.createPendingIntent(context, list.get(3)));

		appWidgetManager.updateAppWidget(appWidgetId, views);
	}

	private static Uri createUri(String imageName) {
		return Uri.parse("content://org.whymca.appwidget.pictures/" + imageName);
	}
}
