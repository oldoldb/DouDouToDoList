package view;

import java.util.ArrayList;
import java.util.List;

import model.ToDoItemInfo;

import com.oldoldb.doudoutodolist.R;

import db.DouDouToDoListDB;
import adapter.ToDoBaseAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

@SuppressLint("ViewConstructor") 
public class ToDoBaseView extends RelativeLayout {
	
	protected ListView mToDoListBaseView;
	protected ToDoBaseAdapter mToDoBaseAdapter;
	protected List<ToDoItemInfo> mToDoItemInfos;
	protected DouDouToDoListDB mDouDouToDoListDB;
	
	private Context mContext;
	private LayoutInflater mInflater;
	public ToDoBaseView(final Context context, ViewGroup rootView) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mDouDouToDoListDB = DouDouToDoListDB.getInstance(mContext);
		mToDoItemInfos = new ArrayList<ToDoItemInfo>();
		initialView(context, rootView);
	}
	@SuppressLint("InlinedApi") 
	private void initialView(final Context context, ViewGroup rootView)
	{
		RelativeLayout todayToDoViewLayout = (RelativeLayout)mInflater.inflate(R.layout.todo_base_view, rootView, false);
		final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)todayToDoViewLayout.findViewById(R.id.swipe_container);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						swipeRefreshLayout.setRefreshing(false);
					}
				}, 5000);
			}
		});
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, 
				android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
		mToDoListBaseView = (ListView)todayToDoViewLayout.findViewById(R.id.list_view);
		this.addView(todayToDoViewLayout);
	}
}
