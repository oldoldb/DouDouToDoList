package com.oldoldb.doudoutodolist;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class CompleteToDoView extends RelativeLayout {

	private LayoutInflater mInflater;
	private ListView mCompleteTodoListView;
	private	CompleteToDoAdapter mCompleteToDoAdapter;
	private Context mContext;
	private List<ToDoItemInfo> mToDoItemInfos = new ArrayList<ToDoItemInfo>();
	private DouDouToDoListDB mDouDouToDoListDB;
	public CompleteToDoView(final Context context, ViewGroup rootView) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		mDouDouToDoListDB = DouDouToDoListDB.getInstance(mContext);
		mToDoItemInfos = mDouDouToDoListDB.loadCompleteToDoItemInfos();
		initialView(context, rootView);
	}
	@SuppressLint("InlinedApi") 
	public void initialView(final Context context, ViewGroup rootView)
	{
		mInflater = LayoutInflater.from(context);
		RelativeLayout todayToDoViewLayout = (RelativeLayout)mInflater.inflate(R.layout.today_todo_view, rootView, false);
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
		mCompleteTodoListView = (ListView)todayToDoViewLayout.findViewById(R.id.list_view);
		mCompleteToDoAdapter = new CompleteToDoAdapter(context, mToDoItemInfos);
		mCompleteTodoListView.setAdapter(mCompleteToDoAdapter);
		this.addView(todayToDoViewLayout);
	}
}
