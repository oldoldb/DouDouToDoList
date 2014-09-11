package adapter;

import java.util.List;

import model.ToDoItemInfo;
import util.DouDouToDoListUtils;
import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oldoldb.doudoutodolist.R;

import db.DouDouToDoListDB;

public class TodayToDoAdapter extends ToDoBaseAdapter {

	private ViewHolder mTouchHolder;
	private boolean mHasTapup;
	private int mDirectionScroll = DouDouToDoListUtils.SCROLL_DIRECTION_NO;
	public TodayToDoAdapter(Context context, List<ToDoItemInfo> toDoItemInfos) {
		super(context, toDoItemInfos);
		// TODO Auto-generated constructor stub
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
				mDirectionScroll = DouDouToDoListUtils.SCROLL_DIRECTION_NO;
				mHasTapup = false;
				gestureDetector.onTouchEvent(event);
				if(mHasTapup){
					showItemDialog(mToDoItemInfos.get(position));
				}else if(mDirectionScroll == DouDouToDoListUtils.SCROLL_DIRECTION_LEFT){
					mTouchHolder.deleteButton.setVisibility(View.VISIBLE);
				}else if(mDirectionScroll == DouDouToDoListUtils.SCROLL_DIRECTION_RIGHT){
					mTouchHolder.deleteButton.setVisibility(View.GONE);
				}
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
				DouDouToDoListDB.getInstance(mContext).updateToDoItemInfo(mToDoItemInfos.get(position));
				refreshData();
			}
		});
		return convertView;
	}

	public void refreshData()
	{
		mToDoItemInfos = DouDouToDoListDB.getInstance(mContext).loadToDoItemInfos();
		notifyDataSetChanged();
	}
	private class MyGestureListener extends SimpleOnGestureListener
	{
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			mHasTapup = true;
		//	showItemDialog(mToDoItemInfo);
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			if(e1.getX() - e2.getX() > 50){
				mDirectionScroll = DouDouToDoListUtils.SCROLL_DIRECTION_LEFT;
			//	mTouchHolder.deleteButton.setVisibility(View.VISIBLE);
			}else if(e2.getX() - e1.getX() > 50){
				mDirectionScroll = DouDouToDoListUtils.SCROLL_DIRECTION_RIGHT;
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
