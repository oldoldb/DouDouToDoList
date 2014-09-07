package com.oldoldb.doudoutodolist;



import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TodayToDoView extends RelativeLayout {
	
	LayoutInflater mInflater;
	ListView mTodayTodoListView;
	TodayToDoAdapter mTodayToDoAdapter;
	List<String> mTodayTitleTodoList = new ArrayList<String>();
	List<String> mTodayDateTodoList = new ArrayList<String>(); 
	public TodayToDoView(final Context context, ViewGroup rootView) {
		super(context);
		// TODO Auto-generated constructor stub
		initialView(context, rootView);
	}
	@SuppressLint("InlinedApi") 
	public void initialView(final Context context, ViewGroup rootView)
	{
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		mTodayTitleTodoList.add("Python");
		mTodayTitleTodoList.add("Android");
		mTodayTitleTodoList.add("Java");
		mTodayDateTodoList.add("2014-09-07");
		mTodayDateTodoList.add("2014-09-08");
		mTodayDateTodoList.add("2014-09-09");
		mTodayTodoListView = (ListView)todayToDoViewLayout.findViewById(R.id.list_view);
		mTodayToDoAdapter = new TodayToDoAdapter(context, mTodayTitleTodoList, mTodayDateTodoList);
		mTodayTodoListView.setAdapter(mTodayToDoAdapter);
		mTodayTodoListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(context, mTodayTitleTodoList.get(position), Toast.LENGTH_SHORT).show();
			}
			
		});
		this.addView(todayToDoViewLayout);
	}

}
