package adapter;

import java.util.List;

import model.ToDoItemInfo;
import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.oldoldb.doudoutodolist.R;

public class CompleteToDoAdapter extends ToDoBaseAdapter {

	private boolean mHasTapup;
	public CompleteToDoAdapter(Context context, List<ToDoItemInfo> toDoItemInfos) {
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
