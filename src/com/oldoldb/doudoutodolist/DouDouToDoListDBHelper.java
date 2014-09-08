package com.oldoldb.doudoutodolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DouDouToDoListDBHelper extends SQLiteOpenHelper {

	public static final String CREATE_COUNTER_INFO = "create table ToDoListInfo ("
			+ "id integer primary key autoincrement, "
			+ "title text, "
			+ "date_year integer, "
			+ "date_month integer, "
			+ "date_day integer, "
			+ "time_hour integer, "
			+ "time_minute integer, "
			+ "is_repeat integer, "
			+ "image_path text, "
			+ "is_complete integer)";
	
	public DouDouToDoListDBHelper(Context context, String name, CursorFactory factory, int version){
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_COUNTER_INFO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
