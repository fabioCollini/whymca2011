package org.whymca.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidgetUtils {

	public static PendingIntent createConfigurePendingIntent(Context context, int appWidgetId, Class<?> configureClass) {
		Intent configureIntent = new Intent(context, configureClass);
		configureIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		return PendingIntent.getActivity(context, -appWidgetId, configureIntent, 0);
	}

	public static Intent createIntent(Context ctx, int pos) {
		Intent intent = new Intent(ctx, PictureViewer.class);
		intent.putExtra(PictureViewer.IMAGE, "img" + pos);
		return intent;
	}

	public static PendingIntent createPendingIntent(Context ctx, int pos) {
		Intent intent = createIntent(ctx, pos);
		return PendingIntent.getActivity(ctx, pos, intent, 0);
	}

	public static void updateWidgetBackground(RemoteViews views, boolean showBackground) {
		if (showBackground) {
			views.setInt(R.id.widget_layout, "setBackgroundResource", R.drawable.background);
		} else {
			views.setInt(R.id.widget_layout, "setBackgroundResource", 0);
		}
	}

}
