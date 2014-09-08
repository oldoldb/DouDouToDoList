package com.oldoldb.doudoutodolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class DouDouToDoListUtils {
	public static Bitmap getCompressBitmap(String filename, int width, int height)
	{
		Log.d("DOUDOU", filename);
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filename, options);
		options.inSampleSize = calculateInSampleSize(options, width, height);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filename);
	}
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
	{
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if(height > reqHeight || width > reqWidth)
		{
			final int heightRatio = Math.round((float)height / (float)reqHeight);
			final int widthRatio = Math.round((float)width / (float)reqWidth);
			inSampleSize = Math.min(heightRatio, widthRatio);
		}
		return inSampleSize;
	}
}
