package com.generations;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name"; 
	// Table creation 
	private static final String TABLE_CREATE =
			"CREATE TABLE " + TABLE_PEOPLE + "("
					+ KEY_ID + " INTEGER PRIMARY KEY,"
					+ KEY_NAME + " TEXT,"
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
		
		cv.put(KEY_NAME, person.getName());
		db.insert(TABLE_PEOPLE, null, cv);
		db.close(); // close db connection
	}
	
	// Getting single person
	Person getPerson(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_PEOPLE, new String[] { KEY_ID, KEY_NAME }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if(cursor != null) {
			cursor.moveToFirst();
		}
		Person person = new Person(cursor.getString(1), true, null, null);
		return person;
	}
	
	public List<Person> getAllContacts() {
        List<Person> personList = new ArrayList<Person>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PEOPLE;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person(cursor.getString(1), true, null, null);
                // Adding contact to list
                personList.add(person);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return personList;
    }
	
	
}
