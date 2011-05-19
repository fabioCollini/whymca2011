package org.whymca.appwidget.alarm;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int appWidgetId = intent.getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_IDS);
		PicturesAppWidgetProviderAlarm.updateAppWidget(context, AppWidgetManager.getInstance(context), appWidgetId);
	}
}
