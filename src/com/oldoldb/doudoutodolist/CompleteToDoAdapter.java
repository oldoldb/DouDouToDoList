package com.oldoldb.doudoutodolist;

import java.util.List;


import android.app.AlertDialog;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CompleteToDoAdapter extends BaseAdapter {

	private Context mContext;
	private List<ToDoItemInfo> mToDoItemInfos;
	private LayoutInflater mInflater;
	private boolean mHasTapup;
	public CompleteToDoAdapter(Context context, List<ToDoItemInfo> toDoItemInfos)
	{
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
		mToDoItemInfos = toDoItemInfos;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mToDoItemInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if(convertView == null)
		{
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.complete_todo_item_view, null);
			viewHolder.titleText = (EditText)convertView.findViewById(R.id.title_text);
			viewHolder.dateText = (TextView)convertView.findViewById(R.id.date_text);
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		final GestureDetector gestureDetector = new GestureDetector(mContext, new MyGestureListener());
		convertView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				mHasTapup = false;
				gestureDetector.onTouchEvent(event);
				if(mHasTapup){
					showItemDialog(mToDoItemInfos.get(position));
				}
				return true;
			}
		});
		viewHolder.titleText.setText(mToDoItemInfos.get(position).getTitle());
		viewHolder.dateText.setText(mToDoItemInfos.get(position).toString());
		return convertView;
	}
	private void showItemDialog(ToDoItemInfo toDoItemInfo)
	{
		LinearLayout linearLayout = (LinearLayout)mInflater.inflate(R.layout.todo_item_detail_view, null);
		TextView titleTextView = (TextView)linearLayout.findViewById(R.id.title_text);
		TextView dateTimeTextView = (TextView)linearLayout.findViewById(R.id.date_time_text);
		CheckBox isRepeatCheckBox = (CheckBox)linearLayout.findViewById(R.id.is_repeat_checkbox);
		ImageView detailiImageView = (ImageView)linearLayout.findViewById(R.id.detal_image);
		titleTextView.setText(toDoItemInfo.getTitle());
		dateTimeTextView.setText(toDoItemInfo.toString());
		isRepeatCheckBox.setSelected(toDoItemInfo.getIs_repeat()==1?true:false);
		detailiImageView.setImageBitmap(DouDouToDoListUtils.getCompressBitmap(toDoItemInfo.getImage_path(), detailiImageView.getWidth(), detailiImageView.getHeight()));
		new AlertDialog.Builder(mContext)
		.setTitle(mContext.getResources().getString(R.string.todo_item_info_title))
		.setView(linearLayout)
		.setNegativeButton(mContext.getResources().getString(R.string.todo_item_info_back_button_default_text), null)
		.show();
	}
	private class MyGestureListener extends SimpleOnGestureListener
	{
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			mHasTapup = true;
			return false;
		}

		
	}
	final static class ViewHolder
	{
		EditText titleText;
		TextView dateText;
	}

}
