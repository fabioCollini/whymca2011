package org.whymca.appwidget.tablet;

import org.whymca.appwidget.service.PicturesAppWidgetProviderService;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.Build;

public class PicturesAppWidgetProviderTablet extends PicturesAppWidgetProviderService {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		if (Build.VERSION.SDK_INT >= 11) {
			for (int i = 0; i < appWidgetIds.length; i++) {
				HoneycombUtils.onUpdateTablet(context, appWidgetManager, appWidgetIds[i]);
			}
		} else {
			super.onUpdate(context, appWidgetManager, appWidgetIds);
		}
	}
}
