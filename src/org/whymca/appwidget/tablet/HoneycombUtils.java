package org.whymca.appwidget.tablet;

import org.whymca.appwidget.PictureViewer;
import org.whymca.appwidget.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class HoneycombUtils {

	public static void onUpdateTablet(Context ctx, AppWidgetManager appWidgetManager, int id) {
		RemoteViews views = new RemoteViews(ctx.getPackageName(), R.layout.scrollable_layout);

		Intent adapterIntent = new Intent(ctx, PicturesService.class);
		adapterIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
		adapterIntent.setData(Uri.parse(adapterIntent.toUri(Intent.URI_INTENT_SCHEME)));
		views.setRemoteAdapter(id, R.id.grid, adapterIntent);

		PendingIntent clickPending = PendingIntent.getActivity(ctx, 0, new Intent(ctx, PictureViewer.class), PendingIntent.FLAG_UPDATE_CURRENT);
		views.setPendingIntentTemplate(R.id.grid, clickPending);

		appWidgetManager.updateAppWidget(id, views);
	}

}
