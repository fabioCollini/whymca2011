package org.whymca.appwidget.tablet;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class PicturesService extends RemoteViewsService {
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return new PicturesViewsFactory(this.getApplicationContext(), intent);
	}
}
