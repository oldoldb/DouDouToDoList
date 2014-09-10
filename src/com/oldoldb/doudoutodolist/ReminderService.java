package com.oldoldb.doudoutodolist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class ReminderService extends Service {

	private NotificationManager mNotificationManager;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		super.onStartCommand(intent, flags, startId);
		mNotificationManager = (NotificationManager)this.getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
		Intent intent2 = new Intent(this.getApplicationContext(), DouDouToDoListActitvity.class);
		intent2.putExtra("isNotification", true);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.ic_launch)
		.setContentTitle(intent.getStringExtra("title"))
		.setWhen(System.currentTimeMillis())
		.setAutoCancel(true);
		builder.setDefaults(Notification.DEFAULT_VIBRATE);
		long[] vibrate = {0,100,200,300};  
        builder.setVibrate(vibrate);  
		intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(pendingIntent);
		mNotificationManager.notify(0, builder.build());
		return START_NOT_STICKY;
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
