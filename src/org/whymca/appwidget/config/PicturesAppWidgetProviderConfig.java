package org.whymca.appwidget.config;

import org.whymca.appwidget.AppWidgetUtils;
import org.whymca.appwidget.PrefsSaver;
import org.whymca.appwidget.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class PicturesAppWidgetProviderConfig extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];
			updateAppWidget(context, appWidgetManager, appWidgetId, PrefsSaver.loadShowBackground(context, appWidgetId));
		}
	}

	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, boolean showBackground) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_provider_config);

		views.setImageViewResource(R.id.img1, R.drawable.img1);
		views.setImageViewResource(R.id.img2, R.drawable.img2);

		views.setOnClickPendingIntent(R.id.configure,
				AppWidgetUtils.createConfigurePendingIntent(context, appWidgetId, PicturesAppWidgetConfigure.class));

		views.setOnClickPendingIntent(R.id.img1, AppWidgetUtils.createPendingIntent(context, 1));
		views.setOnClickPendingIntent(R.id.img2, AppWidgetUtils.createPendingIntent(context, 2));

		AppWidgetUtils.updateWidgetBackground(views, showBackground);

		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
}
