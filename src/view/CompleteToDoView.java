package view;

import adapter.CompleteToDoAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;

@SuppressLint("ViewConstructor") 
public class CompleteToDoView extends ToDoBaseView {

	public CompleteToDoView(Context context, ViewGroup rootView) {
		super(context, rootView);
		// TODO Auto-generated constructor stub
		mToDoItemInfos = mDouDouToDoListDB.loadCompleteToDoItemInfos();
		mToDoBaseAdapter = new CompleteToDoAdapter(context, mToDoItemInfos);
		mToDoListBaseView.setAdapter(mToDoBaseAdapter);
	}

}
