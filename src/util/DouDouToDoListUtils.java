package util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DouDouToDoListUtils {
	public static int REQUEST_CODE_AFTER_ADD_TODO_ITEM = 1;
	public static int REQUEST_CODE_AFTER_TAKE_PHOTO = 2;
	public static int SCROLL_DIRECTION_LEFT = 3;
	public static int SCROLL_DIRECTION_RIGHT = 4;
	public static int SCROLL_DIRECTION_NO = 0;
	public static Bitmap getCompressBitmap(String filename, int width, int height)
	{
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
