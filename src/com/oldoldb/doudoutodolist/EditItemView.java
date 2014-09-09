package com.oldoldb.doudoutodolist;

import java.io.File;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

@SuppressLint("ViewConstructor") 
public class EditItemView extends LinearLayout {

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private LinearLayout mLinearLayout;
	private EditText mTitleEditText;
	private Button mDateButton;
	private Button mTimeButton;
	private Button mPhotoButton;
	private Button mCancelButton;
	private Button mSaveButton;
	private CheckBox mRepeatCheckBox;
	private ImageView mPhotoImageView;
	private LinearLayout mDateLayout;
	private LinearLayout mTimeLayout;
	private DatePicker mDatePicker;
	private TimePicker mTimePicker;
	private String mDateString;
	private String mTimeString;
	private DouDouToDoListDB mDouDouToDoListDB;
	private ToDoItemInfo mToDoItemInfo;
	public EditItemView(Context context, ViewGroup rootView)
	{
		super(context);
		mContext = context;
		mDouDouToDoListDB = DouDouToDoListDB.getInstance(mContext);
		mToDoItemInfo = new ToDoItemInfo();
		initialViews(rootView);
	}
	private void initialViews(ViewGroup rootView)
	{
		mLayoutInflater = LayoutInflater.from(mContext);
		mLinearLayout = (LinearLayout)mLayoutInflater.inflate(R.layout.edit_item_view, rootView, false);
		addView(mLinearLayout);
		mTitleEditText = (EditText)mLinearLayout.findViewById(R.id.title_text);
		mDateButton = (Button)mLinearLayout.findViewById(R.id.date_button);
		mTimeButton = (Button)mLinearLayout.findViewById(R.id.time_button);
		mPhotoButton = (Button)mLinearLayout.findViewById(R.id.add_photo_button);
		mCancelButton = (Button)mLinearLayout.findViewById(R.id.cancel_button);
		mSaveButton = (Button)mLinearLayout.findViewById(R.id.save_button);
		mRepeatCheckBox = (CheckBox)mLinearLayout.findViewById(R.id.is_repeat_checkbox);
		mPhotoImageView = (ImageView)mLinearLayout.findViewById(R.id.photo_image_view);
		mDateButton.setOnClickListener(mOnClickListener);
		mTimeButton.setOnClickListener(mOnClickListener);
		mPhotoButton.setOnClickListener(mOnClickListener);
		mCancelButton.setOnClickListener(mOnClickListener);
		mSaveButton.setOnClickListener(mOnClickListener);
	}
	
	View.OnClickListener mOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.add_photo_button:
				onPhotoButtonClicked();
				break;
			case R.id.date_button:
				onDateButtonClicked();
				break;
			case R.id.time_button:
				onTimeButtonClicked();
				break;
			case R.id.save_button:
				onSaveButtonClicked();
			default:
				break;
			}
		}
	};
	private void testest()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 1);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.AM_PM, Calendar.PM);
		Intent intent = new Intent(mContext, ReminderReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager)mContext.getSystemService(Activity.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);;
	}
	private void onSaveButtonClicked()
	{
		mToDoItemInfo.setTitle(mTitleEditText.getText().toString());
		mToDoItemInfo.setIs_repeat(mRepeatCheckBox.isChecked()?1:0);
		mDouDouToDoListDB.addToDoItemInfo(mToDoItemInfo);
	//	addNotification();
		testest();
	}
	private void onDateButtonClicked()
	{
		mDateLayout = (LinearLayout)mLayoutInflater.inflate(R.layout.date_dialog, null);
		mDatePicker = (DatePicker)mDateLayout.findViewById(R.id.date_picker);
		Calendar calendar = Calendar.getInstance();
		mDatePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				mDateString = year + "-" + monthOfYear + "-" + dayOfMonth;
				mToDoItemInfo.setDate_year(year);
				mToDoItemInfo.setDate_month(monthOfYear);
				mToDoItemInfo.setDate_day(dayOfMonth);
			}
		});
		new AlertDialog.Builder(mContext)
		.setTitle(getResources().getString(R.string.date_dialog_title))
		.setView(mDateLayout)
		.setPositiveButton(getResources().getString(R.string.date_dialog_save_button_default_text), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				mDateButton.setText(mDateString);
			}
		})
		.setNegativeButton(getResources().getString(R.string.date_dialog_cancel_button_default_text), null)
		.show();
	}
	private void onTimeButtonClicked()
	{
		mTimeLayout = (LinearLayout)mLayoutInflater.inflate(R.layout.time_dialog, null);
		mTimePicker = (TimePicker)mTimeLayout.findViewById(R.id.time_picker);
		mTimePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				mTimeString = hourOfDay + ":" + minute;
				mToDoItemInfo.setTime_hour(hourOfDay);
				mToDoItemInfo.setTime_minute(minute);
			}
		});
		new AlertDialog.Builder(mContext)
		.setTitle(getResources().getString(R.string.time_dialog_title))
		.setView(mTimeLayout)
		.setPositiveButton(getResources().getString(R.string.time_dialog_save_button_default_text), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				mTimeButton.setText(mTimeString);
			}
		})
		.setNegativeButton(getResources().getString(R.string.time_dialog_cancel_button_default_text), null)
		.show();
	}
	private void onPhotoButtonClicked()
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Activity hostActivity = (Activity)mContext;
		hostActivity.startActivityForResult(intent, 2);
	}
	
	public void showImageView(File imageFile)
	{
		mPhotoImageView.setImageBitmap(DouDouToDoListUtils.getCompressBitmap(imageFile.getPath(), mPhotoImageView.getWidth(), mPhotoImageView.getHeight()));
		mToDoItemInfo.setImage_path(imageFile.getPath());
	}
	
	
}
