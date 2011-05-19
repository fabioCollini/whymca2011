package org.whymca.appwidget;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;

public class ImageContentProvider extends ContentProvider {

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		return null;
	}

	@Override
	public boolean onCreate() {
		return false;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3, String arg4) {
		return null;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		return 0;
	}

	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
		String imageName = uri.getPath().substring(1);
		File tmpFile = getTempFile(getContext(), imageName);
		if (!tmpFile.exists()) {
			copyAssetFile(getContext(), imageName, imageName + ".JPG");
		}

		try {
			return ParcelFileDescriptor.open(tmpFile, ParcelFileDescriptor.MODE_READ_ONLY);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static File getTempFile(Context context, String tmpFileName) {
		if (isSdMounted()) {
			return getSdCardFile(context, tmpFileName);
		}
		return context.getFileStreamPath(tmpFileName);
	}

	private static File getSdCardFile(Context context, String tmpFileName) {
		return new File(getSdCardDir(context), tmpFileName + ".dat");
	}

	private static File getSdCardDir(Context context) {
		String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + context.getPackageName() + "/cache/";
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
			try {
				new File(dir, ".nomedia").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	private static boolean isSdMounted() {
		String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state);
	}

	public static void copyAssetFile(Context context, String tmpFileName, String assetFileName) {
		BufferedInputStream fis = null;
		BufferedOutputStream fos = null;
		try {
			fis = new BufferedInputStream(context.getAssets().open(assetFileName));
			fos = new BufferedOutputStream(createFileOutput(context, tmpFileName));

			byte[] dati = new byte[fis.available()];
			fis.read(dati);
			fos.write(dati);

			fis.close();
			fos.close();

			fos.flush();
			fos.close();
		} catch (IOException e) {
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e1) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	private static FileOutputStream createFileOutput(Context context, String tmpFileName) throws FileNotFoundException {
		if (isSdMounted()) {
			return new FileOutputStream(getSdCardFile(context, tmpFileName));
		}
		return context.openFileOutput(tmpFileName, Activity.MODE_PRIVATE);
	}

}
