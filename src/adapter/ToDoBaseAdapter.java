package adapter;

import java.io.File;
import java.util.List;

import com.oldoldb.doudoutodolist.R;

import model.ToDoItemInfo;
import util.DouDouToDoListUtils;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ToDoBaseAdapter extends BaseAdapter {

	protected Context mContext;
	protected List<ToDoItemInfo> mToDoItemInfos;
	protected LayoutInflater mInflater;

	public ToDoBaseAdapter(Context context, List<ToDoItemInfo> toDoItemInfos)
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected  void showItemDialog(final ToDoItemInfo toDoItemInfo)
	{
		LinearLayout linearLayout = (LinearLayout)mInflater.inflate(R.layout.todo_item_detail_view, null);
		TextView titleTextView = (TextView)linearLayout.findViewById(R.id.title_text);
		TextView dateTimeTextView = (TextView)linearLayout.findViewById(R.id.date_time_text);
		CheckBox isRepeatCheckBox = (CheckBox)linearLayout.findViewById(R.id.is_repeat_checkbox);
		ImageView detailiImageView = (ImageView)linearLayout.findViewById(R.id.detal_image);
		titleTextView.setText(toDoItemInfo.getTitle());
		dateTimeTextView.setText(toDoItemInfo.toString());
		isRepeatCheckBox.setChecked(toDoItemInfo.getIs_repeat()==1?true:false);
		if(toDoItemInfo.getImage_path().trim().length() == 0){
			detailiImageView.setVisibility(View.GONE);
		}else{
			detailiImageView.setImageBitmap(DouDouToDoListUtils.getCompressBitmap(toDoItemInfo.getImage_path(), detailiImageView.getWidth(), detailiImageView.getHeight()));
			detailiImageView.setOnClickListener(new OnClickListener() {
			
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW);
					intent.setDataAndType(Uri.fromFile(new File(toDoItemInfo.getImage_path())), "image/*");
					mContext.startActivity(intent);
				}
			});
		}
		new AlertDialog.Builder(mContext)
		.setTitle(mContext.getResources().getString(R.string.todo_item_info_title))
		.setView(linearLayout)
		.setNegativeButton(mContext.getResources().getString(R.string.todo_item_info_back_button_default_text), null)
		.show();
	}	
}
