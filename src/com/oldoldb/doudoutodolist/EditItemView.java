package com.oldoldb.doudoutodolist;

import java.util.Calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class EditItemView extends LinearLayout {

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private LinearLayout mLinearLayout;
	private Button mDateButton;
	private Button mTimeButton;
	private Button mPhotoButton;
	private Button mCancelButton;
	private Button mSaveButton;
	private LinearLayout mDateLayout;
	private LinearLayout mTimeLayout;
	private DatePicker mDatePicker;
	private TimePicker mTimePicker;
	private String mDateString;
	private String mTimeString;
	public EditItemView(Context context, ViewGroup rootView)
	{
		super(context);
		mContext = context;
		initialViews(rootView);
	}
	private void initialViews(ViewGroup rootView)
	{
		mLayoutInflater = LayoutInflater.from(mContext);
		mLinearLayout = (LinearLayout)mLayoutInflater.inflate(R.layout.edit_item_view, rootView, false);
		addView(mLinearLayout);
		mDateButton = (Button)mLinearLayout.findViewById(R.id.date_button);
		mTimeButton = (Button)mLinearLayout.findViewById(R.id.time_button);
		mPhotoButton = (Button)mLinearLayout.findViewById(R.id.add_photo_button);
		mCancelButton = (Button)mLinearLayout.findViewById(R.id.cancel_button);
		mSaveButton = (Button)mLinearLayout.findViewById(R.id.save_button);
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
			case R.id.date_button:
				mDateLayout = (LinearLayout)mLayoutInflater.inflate(R.layout.date_dialog, null);
				mDatePicker = (DatePicker)mDateLayout.findViewById(R.id.date_picker);
				Calendar calendar = Calendar.getInstance();
				mDatePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {
					
					@Override
					public void onDateChanged(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						mDateString = year + "-" + monthOfYear + "-" + dayOfMonth;
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
				break;
			case R.id.time_button:
				mTimeLayout = (LinearLayout)mLayoutInflater.inflate(R.layout.time_dialog, null);
				mTimePicker = (TimePicker)mTimeLayout.findViewById(R.id.time_picker);
				mTimePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
					
					@Override
					public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						mTimeString = hourOfDay + ":" + minute;
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
				break;
			default:
				break;
			}
		}
	};
}
