package org.whymca.appwidget;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PrefsSaver {
	private static final String PREF_PREFIX_KEY = "pictures_widget_";

	public static void saveShowBackground(Context context, int appWidgetId, boolean checked) {
		Editor prefs = PreferenceManager.getDefaultSharedPreferences(context).edit();
		prefs.putBoolean(PREF_PREFIX_KEY + appWidgetId, checked);
		prefs.commit();
	}

	public static boolean loadShowBackground(Context context, int appWidgetId) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getBoolean(PREF_PREFIX_KEY + appWidgetId, false);
	}
}
