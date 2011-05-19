package org.whymca.appwidget.tablet2;

import org.whymca.appwidget.PictureViewer;
import org.whymca.appwidget.R;
import org.whymca.appwidget.service.PicturesAppWidgetProviderService;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class PicturesAppWidgetProviderTablet2 extends PicturesAppWidgetProviderService {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];

			onUpdateTablet(context, appWidgetManager, appWidgetId);
		}
	}

	public void onUpdateTablet(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
		Intent adapterIntent = new Intent(context, PicturesService2.class);

		adapterIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		adapterIntent.setData(Uri.parse(adapterIntent.toUri(Intent.URI_INTENT_SCHEME)));

		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.scrollable_layout_2);

		views.setRemoteAdapter(appWidgetId, R.id.grid, adapterIntent);

		Intent clickIntent = new Intent(context, PictureViewer.class);
		PendingIntent clickPending = PendingIntent.getActivity(context, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		views.setPendingIntentTemplate(R.id.grid, clickPending);

		appWidgetManager.updateAppWidget(appWidgetId, views);
	}

}
