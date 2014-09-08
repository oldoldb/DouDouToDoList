package com.oldoldb.doudoutodolist;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DouDouToDoListDB {

public static final String DB_NAME = "doudou_todolist_db";
	
	public static final String TABLE_NAME = "ToDoListInfo";

	public static final int VERSION = 1;
	private static DouDouToDoListDB douDouToDoListDB;
	private SQLiteDatabase db;
	

	private DouDouToDoListDB(Context context){
		DouDouToDoListDBHelper dbHelper = new DouDouToDoListDBHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	

	public synchronized static DouDouToDoListDB getInstance(Context context){
		if(douDouToDoListDB == null){
			douDouToDoListDB = new DouDouToDoListDB(context);
		}
		return douDouToDoListDB;
	}	

	public void addToDoItemInfo(ToDoItemInfo toDoItemInfo){
		if(toDoItemInfo != null)
		{
			ContentValues values = new ContentValues();
			values.put("title", toDoItemInfo.getTitle());
			values.put("date_year", toDoItemInfo.getDate_year());
			values.put("date_month", toDoItemInfo.getDate_month());
			values.put("date_day", toDoItemInfo.getDate_day());
			values.put("time_hour", toDoItemInfo.getTime_hour());
			values.put("time_minute", toDoItemInfo.getTime_minute());
			values.put("is_repeat", toDoItemInfo.getIs_complete());
			values.put("image_path", toDoItemInfo.getImage_path());
			values.put("is_complete", toDoItemInfo.getIs_complete());
			db.insert(TABLE_NAME, null, values);
		}
	}
	public List<ToDoItemInfo> loadToDoItemInfos(){
		List<ToDoItemInfo> list = new ArrayList<ToDoItemInfo>();
		Cursor cursor = db.query(TABLE_NAME, null, "is_complete = ?", new String[]{"0"}, null, null, null);
		System.out.println(cursor == null);
		if(cursor.moveToFirst())
		{
			do{
				ToDoItemInfo toDoItemInfo = new ToDoItemInfo();
				toDoItemInfo.setTitle(cursor.getString(cursor.getColumnIndex("title")));
				toDoItemInfo.setDate_year(cursor.getInt(cursor.getColumnIndex("date_year")));
				toDoItemInfo.setDate_month(cursor.getInt(cursor.getColumnIndex("date_month")));
				toDoItemInfo.setDate_day(cursor.getInt(cursor.getColumnIndex("date_day")));
				toDoItemInfo.setTime_hour(cursor.getInt(cursor.getColumnIndex("time_hour")));
				toDoItemInfo.setTime_minute(cursor.getInt(cursor.getColumnIndex("time_minute")));
				toDoItemInfo.setIs_repeat(cursor.getInt(cursor.getColumnIndex("is_repeat")));
				toDoItemInfo.setIs_complete(cursor.getInt(cursor.getColumnIndex("is_complete")));
				toDoItemInfo.setImage_path(cursor.getString(cursor.getColumnIndex("image_path")));
				list.add(toDoItemInfo);
			} while(cursor.moveToNext());
		}
		return list;
	}
}
