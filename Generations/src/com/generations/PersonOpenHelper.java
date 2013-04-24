package com.generations;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonOpenHelper extends SQLiteOpenHelper {
	
	private final String TAG = "SQLHelper";
	
	// Database version
	private static final int DATABASE_VERSION = 1;
	// Database name
	private static final String DATABASE_NAME = "fulltree";
	// Table name
	private static final String TABLE_PEOPLE = "people";
	// Column names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name"; 
	private static final String KEY_HAIR = "hair";
	private static final String KEY_EYE = "eye";
	private static final String KEY_PEAK = "peak";
	private static final String KEY_EAR = "ear";
	private static final String KEY_CHIN = "chin";
	// Table creation 
	private static final String TABLE_CREATE =
			"CREATE TABLE " + TABLE_PEOPLE + "("
					+ KEY_ID + " INT PRIMARY KEY,"
					+ KEY_NAME + " TEXT"
					+ KEY_HAIR + " TEXT"
					+ KEY_EYE + " TEXT"
					+ KEY_PEAK + " TEXT"
					+ KEY_EAR + " TEXT"
					+ KEY_CHIN + " TEXT"
					+ ")";
	
	public PersonOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	public void deleteDatabase(Context context) {
		context.deleteDatabase(DATABASE_NAME);
	}

	// Creates the table 
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, TABLE_CREATE);
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
		cv.put(KEY_HAIR, person.getTrait("Hair Color").getTrait());
		cv.put(KEY_EYE, person.getTrait("Eye Color").getTrait());
		cv.put(KEY_PEAK, person.getTrait("Widows Peak").getTrait());
		cv.put(KEY_EAR, person.getTrait("Connected Earlobes").getTrait());
		cv.put(KEY_CHIN, person.getTrait("Cleft Chin").getTrait());
		db.insert(TABLE_PEOPLE, null, cv);
		db.close(); // close db connection
	}
	
	// Getting single person
	public Person getPerson(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_PEOPLE, new String[] { KEY_ID, KEY_NAME }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if(cursor != null) {
			cursor.moveToFirst();
		}
		Person person = new Person(cursor.getString(1), true, id, null, null);
		person.getTrait("Hair Color").setTrait(cursor.getString(2));
		person.getTrait("Eye Color").setTrait(cursor.getString(3));
		person.getTrait("Widows Peak").setTrait(cursor.getString(4));
		person.getTrait("Connected Earlobes").setTrait(cursor.getString(5));
		person.getTrait("Cleft Chin").setTrait(cursor.getString(6));
		return person;
	}
	
	// Get all people
	public List<Person> getAllPeople() {
        List<Person> personList = new ArrayList<Person>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PEOPLE;
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person(cursor.getString(1), true, i, null, null);
        		person.getTrait("Hair Color").setTrait(cursor.getString(2));
        		person.getTrait("Eye Color").setTrait(cursor.getString(3));
        		person.getTrait("Widows Peak").setTrait(cursor.getString(4));
        		person.getTrait("Connected Earlobes").setTrait(cursor.getString(5));
        		person.getTrait("Cleft Chin").setTrait(cursor.getString(6));
                i++;
                // Adding contact to list
                personList.add(person);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return personList;
    }
	
	// Updating single person
	public int updatePerson(Person person) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, person.getName());
		
		// updating row
		return db.update(TABLE_PEOPLE, cv, KEY_ID + "=?", new String[] { String.valueOf(person.getId()) });
	}
	
	// Deleting single person
	public void deletePerson(Person person) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PEOPLE, KEY_ID + "=?", new String[] { String.valueOf(person.getId()) });
		db.close();
	}
	
	public void destroyTable() {
		String destroy = "DROP TABLE " + TABLE_PEOPLE;
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(destroy);
		db.close();
	}
	
	// Getting people count
	public int getPeopleCount() {
		String countQuery = "SELECT  * FROM " + TABLE_PEOPLE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		return cursor.getCount();
	}
}
