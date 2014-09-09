package com.oldoldb.doudoutodolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReminderReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent serviceIntent = new Intent(context, ReminderService.class);
		context.startService(serviceIntent);
	}

}
