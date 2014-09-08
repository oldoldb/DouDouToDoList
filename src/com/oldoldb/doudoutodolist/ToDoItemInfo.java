package com.oldoldb.doudoutodolist;

import java.util.Calendar;

import android.text.format.Time;

@SuppressWarnings("rawtypes")
public class ToDoItemInfo implements Comparable {

		private int id;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		private int date_year;
		public int getDate_year() {
			return date_year;
		}
		public void setDate_year(int date_year) {
			this.date_year = date_year;
		}
		public int getDate_month() {
			return date_month;
		}
		public void setDate_month(int date_month) {
			this.date_month = date_month;
		}
		public int getDate_day() {
			return date_day;
		}
		public void setDate_day(int date_day) {
			this.date_day = date_day;
		}
		public int getTime_hour() {
			return time_hour;
		}
		public void setTime_hour(int time_hour) {
			this.time_hour = time_hour;
		}
		public int getTime_minute() {
			return time_minute;
		}
		public void setTime_minute(int time_minute) {
			this.time_minute = time_minute;
		}
		public int getIs_repeat() {
			return is_repeat;
		}
		public void setIs_repeat(int is_repeat) {
			this.is_repeat = is_repeat;
		}
		public String getImage_path() {
			return image_path;
		}
		public void setImage_path(String image_path) {
			this.image_path = image_path;
		}
		public int getIs_complete() {
			return is_complete;
		}
		public void setIs_complete(int is_complete) {
			this.is_complete = is_complete;
		}
		private String title;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		private int date_month;
		private int date_day;
		private int time_hour;
		private int time_minute;
		private int is_repeat;
		private String image_path;
		private int is_complete;
		public ToDoItemInfo()
		{
			Calendar calendar = Calendar.getInstance();
			date_year = calendar.get(Calendar.YEAR);
			date_month = calendar.get(Calendar.MONTH);
			date_day = calendar.get(Calendar.DAY_OF_MONTH);
			Time time = new Time();
			time_hour = time.hour;
			time_minute = time.minute;
			is_repeat = 1;
			is_complete = 0;
			title = "";
			image_path = "";
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return date_year + "-" + date_month + "-" + date_day ;
		}
		@Override
		public int compareTo(Object another) {
			// TODO Auto-generated method stub
			ToDoItemInfo exerciseInfo2 = (ToDoItemInfo)another;
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(this.date_year, this.date_month, this.date_day);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(exerciseInfo2.getDate_year(), exerciseInfo2.getDate_month(), exerciseInfo2.getDate_day());
			if(calendar1.before(calendar2))
			{
				return 1;
			}
			else if(calendar1.equals(calendar2))
			{
				return 0;
			}
			else 
			{
				return -1;
			}
		}
}
