package com.com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "laiyifen.db";
	//建表语句
	private String shopping_shopping_basket_table = "CREATE TABLE IF NOT EXISTS shopping_shopping_basket " +
	"(_id INTEGER PRIMARY KEY AUTOINCREMENT,shopping_basket_name VARCHAR,shopping_basket_price VARCHAR," +
	"shopping_basket_image VARCHAR ,shopping_basket_count INTEGER)";  
  
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(shopping_shopping_basket_table);  
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
