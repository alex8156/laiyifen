package com.com.db;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.cxl.shoppingcar.ShoppingCar;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private static final String SHOPPING_BASKET = "shopping_shopping_basket";
	
	public DBManager(Context context) {
		dbHelper = new DBHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	public int querryBasketCount() {
		// TODO Auto-generated method stub
		int count = 0;
		Cursor cursor = db.query(SHOPPING_BASKET, null, null, null, null, null, null);
		
		if (cursor !=null &&cursor.moveToFirst()) {
			int countCursor = cursor.getCount();
			for (int i = 0; i < countCursor; i++) {
				cursor.moveToPosition(i);
				count += cursor.getInt(cursor.getColumnIndexOrThrow("shopping_basket_count"));
			}
			
			
		}
		
		return count;
	}

	public ArrayList<ShoppingCar> querryBasket() {
		// TODO Auto-generated method stub
		ArrayList<ShoppingCar> shoppingCars = new ArrayList<ShoppingCar>();
		ShoppingCar shoppingCar = null;
		Cursor cursor = db.query(SHOPPING_BASKET, null, null, null, null, null, null);
		
		if (cursor !=null &&cursor.moveToFirst()) {
			int countCursor = cursor.getCount();
			for (int i = 0; i < countCursor; i++) {
				cursor.moveToPosition(i);
				int shoppingCarID = cursor.getInt(cursor.getColumnIndex("_id"));
				boolean isSelected = false;
				String imagePath = cursor.getString(cursor.getColumnIndex("shopping_basket_image"));
				String foodInfo = cursor.getString(cursor.getColumnIndex("shopping_basket_name"));
				String unitPrice = cursor.getString(cursor.getColumnIndex("unitPrice"));
				String decrease = "";
				int foodCount = cursor.getInt(cursor.getColumnIndex("shopping_basket_count"));
				String increase = "";
				shoppingCar = new ShoppingCar(shoppingCarID, isSelected, imagePath, foodInfo, unitPrice, decrease, foodCount, increase);
				shoppingCars.add(shoppingCar);
			}
			
		}
		
		return shoppingCars;
	}
	
	
	public void insertBasket(String shopping_basket_name,
			String shopping_basket_price, String shopping_basket_image) {
		// TODO Auto-generated method stub
		Cursor cursor = db.query(SHOPPING_BASKET, null, "shopping_basket_name=?", new String[]{shopping_basket_name}, null, null, null);
		
		if (cursor !=null&&cursor.moveToFirst()) {
			int count = cursor.getCount();
			for (int i = 0; i < count; i++) {
				cursor.moveToPosition(i);
				int shopping_basket_count = cursor.getInt(cursor.getColumnIndexOrThrow("shopping_basket_count"));
				String sql = "update " + SHOPPING_BASKET + " set shopping_basket_count = ? where shopping_basket_name = ?";
				System.out.println(sql);
				db.execSQL(sql, new Object[] {shopping_basket_count+1,shopping_basket_name});
			}
			
		} else {
			ContentValues contentValues = new ContentValues();
			contentValues.put("shopping_basket_name", shopping_basket_name);
			contentValues.put("shopping_basket_price", shopping_basket_price);
			contentValues.put("shopping_basket_image", shopping_basket_image);
			contentValues.put("shopping_basket_count", 1);
			db.insert(SHOPPING_BASKET, null, contentValues);
			
		}
	}
	
	public void closeDB() {
		db.close();
	}

}
