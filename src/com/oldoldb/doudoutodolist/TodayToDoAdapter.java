package com.oldoldb.doudoutodolist;

import java.util.List;

import android.content.Context;
import android.util.Log;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TodayToDoAdapter extends BaseAdapter {

	private List<String> mTitleList;
	private List<String> mDateList;
	private Context mContext;
	public TodayToDoAdapter(Context context, List<String> titleList, List<String> dateList)
	{
		mContext = context;
		mTitleList = titleList;
		mDateList = dateList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTitleList.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
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
				final ViewHolder holder = (ViewHolder)v.getTag();
				if(gestureDetector.onTouchEvent(event)){
					holder.deleteButton.setVisibility(View.VISIBLE);
				}
				return true;
			}
		});
		viewHolder.titleText.setText(mTitleList.get(position));
		viewHolder.dateText.setText(mDateList.get(position));
		Log.d("DOUDOU", viewHolder.titleText.getText() + " , " + viewHolder.dateText.getText());
		viewHolder.deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "RIGHT", Toast.LENGTH_SHORT).show();
				v.setVisibility(View.GONE);
			}
		});
		return convertView;
	}
	
	private class MyGestureListener extends SimpleOnGestureListener
	{

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			if(e1.getX() - e2.getX() > 50){
				return true;
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
