package org.whymca.appwidget.tablet2;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class PicturesService2 extends RemoteViewsService {
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return new PicturesViewsFactory2(this.getApplicationContext(), intent);
	}
}
