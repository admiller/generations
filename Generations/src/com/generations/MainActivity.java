package com.generations;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	private static Person user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//TODO Kriti: Insert SQL Loading code here that will load previously made entries in the tree
		PersonOpenHelper poh = new PersonOpenHelper(this);
		SQLiteDatabase db = null;
		try {
			db = poh.getReadableDatabase();
		} catch(SQLiteException e) {
			Log.e(TAG, "Database could not be opened");
			e.printStackTrace();
		}
		if(db == null) {
			Log.e(TAG, "DB was null");
			
		}
		// Make sure that you set the User and all of their family upon load
		// for now we will just use the following lines
		Person mainUser = new Person("User", true, null, null);
		Person mom = new Person("Parent 1", false, null, null);
		Person dad = new Person("Parent 2", true, null, null);
		Person gp1 = new Person("GParent 1", true, null, null);
		Person gp2 = new Person("GParent 2", false, null, null);
		Person gp3 = new Person("GParent 3", true, null, null);
		Person gp4 = new Person("GParent 4", false, null, null);
		mainUser.setParent(1, mom);
		mainUser.setParent(2, dad);
		mom.setParent(1, gp1);
		mom.setParent(2, gp2);
		dad.setParent(1, gp3);
		dad.setParent(2, gp4);
		createTraits(mainUser);
		createTraits(mom);
		createTraits(dad);
		createTraits(gp1);
		createTraits(gp2);
		createTraits(gp3);
		createTraits(gp4);
		setUser(mainUser);
	}
	
	public static void createTraits(Person person) {
		person.addTrait(new Trait("Hair Color", "Brown"));
		person.addTrait(new Trait("Eye Color", "Blue"));
		person.addTrait(new Trait("Widows Peak", "yes"));
		person.addTrait(new Trait("Connected Earlobes", "no"));
		person.addTrait(new Trait("Cleft Chin", "yes"));
	}
	
	/**
	 * Gets the singleton instance of the main user. If no user is specified
	 * a default user is created
	 * @return The main user
	 */
	public static Person getUser() {
		if(user == null) {
			user = new Person("Default User", true, null, null);
		}
		return user;
	}
	
	private void setUser(Person user) {
		MainActivity.user = user;
	}
	
	public void connectToUser(View view) {
		//TODO Adam: Put Code to connect to other user in here
		//TODO Everyone - FYI 
		/* If you need to start a new activity (called ExampleActivity here)
		 * then insert the following code:
		 * 
		 *	Intent intent = new Intent(this, ExampleActivity.class);
		 *	startActivity(intent);
		 */
		Intent intent = new Intent(this, ConnectionActivity.class);
		startActivity(intent);
	}
	
	public void displayTree(View view) {
		Intent intent = new Intent(this, TreeActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
