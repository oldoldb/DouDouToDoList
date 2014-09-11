package view;

import adapter.TodayToDoAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;

@SuppressLint("ViewConstructor") 
public class TodayToDoView extends ToDoBaseView {

	public TodayToDoView(Context context, ViewGroup rootView) {
		super(context, rootView);
		// TODO Auto-generated constructor stub
		mToDoItemInfos = mDouDouToDoListDB.loadToDoItemInfos();
		mToDoBaseAdapter = new TodayToDoAdapter(context, mToDoItemInfos);
		mToDoListBaseView.setAdapter(mToDoBaseAdapter);
	}
	
}
