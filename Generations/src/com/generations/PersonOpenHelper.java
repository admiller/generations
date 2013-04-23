package com.generations;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonOpenHelper extends SQLiteOpenHelper {
	
	// Database version
	private static final int DATABASE_VERSION = 1;
	// Database name
	private static final String DATABASE_NAME = "familiyTree";
	// Table name
	private static final String TABLE_PEOPLE = "people";
	// Column names
	private static final String KEY_NAME = "name"; 
	// Table creation 
	private static final String TABLE_CREATE =
			"CREATE TABLE " + TABLE_PEOPLE + "("
					+ KEY_NAME + " TEXT"
					+ ");";
	
	public PersonOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	// Creates the table 
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
	}
	
	// Upgrades database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE);
		// Create tables again
		onCreate(db);
	}
	
	/**
	 * All CRUD(Create, Read, Update, Delete) Ops
	 */
	// Adding new person
	public void addPerson(Person person) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
	}
	
	
}
