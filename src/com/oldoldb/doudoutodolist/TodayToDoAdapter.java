package com.oldoldb.doudoutodolist;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TodayToDoAdapter extends BaseAdapter {

	private Context mContext;
	private List<ToDoItemInfo> mToDoItemInfos;
	private ToDoItemInfo mToDoItemInfo;
	private LayoutInflater mInflater;
	private ViewHolder mTouchHolder;
	public TodayToDoAdapter(Context context, List<ToDoItemInfo> toDoItemInfos)
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.today_todo_item_view, null);
			viewHolder.titleText = (EditText)convertView.findViewById(R.id.title_text);
			viewHolder.deleteButton = (Button)convertView.findViewById(R.id.button_delete);
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
				mTouchHolder = (ViewHolder)v.getTag();
				mToDoItemInfo = mToDoItemInfos.get(position);
				gestureDetector.onTouchEvent(event);
				return true;
			}
		});
		viewHolder.titleText.setText(mToDoItemInfos.get(position).getTitle());
		viewHolder.dateText.setText(mToDoItemInfos.get(position).toString());
		viewHolder.deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v.setVisibility(View.GONE);
			}
		});
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
		dateTimeTextView.setText(toDoItemInfo.toString() + "   " + toDoItemInfo.getTime_hour() + ":" + toDoItemInfo.getTime_minute());
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
			showItemDialog(mToDoItemInfo);
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			if(e1.getX() - e2.getX() > 50){
				mTouchHolder.deleteButton.setVisibility(View.VISIBLE);
			}
			return false;
		}
		
	}
	final static class ViewHolder
	{
		EditText titleText;
		TextView dateText;
		Button deleteButton;
	}

}
