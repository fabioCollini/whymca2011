package org.whymca.appwidget.tablet;

import org.whymca.appwidget.PictureViewer;
import org.whymca.appwidget.R;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class PicturesViewsFactory implements RemoteViewsService.RemoteViewsFactory {
	@Override
	public RemoteViews getViewAt(int position) {
		RemoteViews row = new RemoteViews(ctxt.getPackageName(), R.layout.scrollable_cell);

		row.setImageViewUri(R.id.appwidget_image, createUri("img" + (position + 1)));

		Intent intent = new Intent();
		intent.putExtra(PictureViewer.IMAGE, "img" + (position + 1));
		row.setOnClickFillInIntent(R.id.appwidget_image, intent);

		return row;
	}

	private static Uri createUri(String imageName) {
		return Uri.parse("content://org.whymca.appwidget.pictures/" + imageName);
	}

	private Context ctxt = null;

	public PicturesViewsFactory(Context ctxt, Intent intent) {
		this.ctxt = ctxt;
	}

	@Override
	public void onCreate() {
	}

	@Override
	public void onDestroy() {
	}

	@Override
	public int getCount() {
		return 6;
	}

	@Override
	public RemoteViews getLoadingView() {
		return null;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public void onDataSetChanged() {
	}
}
