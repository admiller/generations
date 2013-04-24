package com.generations;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
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

		// Reading database
		PersonOpenHelper db = new PersonOpenHelper(this);
		//db.deleteDatabase(this);
		try {
			Log.i(TAG, "Reading people from db");
			List<Person> people = db.getAllPeople();
			for (Person p : people) {
				String log = "ID: " + p.getId() + ", Name: " + p.getName();
				Log.d(TAG, log);
			}
			Log.i(TAG, "Number of People Loaded: " + people.size());
			if (people.size() > 0) {
				Person main = people.get(0);
				Person p1 = people.get(1);
				Person p2 = people.get(2);
				Person gp1 = people.get(3);
				Person gp2 = people.get(4);
				Person gp3 = people.get(5);
				Person gp4 = people.get(6);
				main.setParent(1, p1);
				main.setParent(2, p2);
				p1.setParent(1, gp1);
				p1.setParent(2, gp2);
				p2.setParent(1, gp3);
				p2.setParent(2, gp4);
				Log.i(TAG, "Main Name: " + main.getName());
				Log.i(TAG, "P1 Name: " + p1.getName());
				Log.i(TAG, "P2 Name: " + p2.getName());
				Log.i(TAG, "GP1 Name: " + gp1.getName());
				Log.i(TAG, "GP2 Name: " + gp2.getName());
				Log.i(TAG, "GP3 Name: " + gp3.getName());
				Log.i(TAG, "GP4 Name: " + gp4.getName());
				for(int i = 0; i < 7; i++) {
					createTraits(people.get(i));
				}
				setUser(main);
			} else {
				createDefaultTree();
			}
		} catch (SQLiteException e) {
			createDefaultTree();
		}
	}

	public void createDefaultTree() {
		Person mainUser = new Person("User", true, 0, null, null);
		Person mom = new Person("Parent 1", false, 1, null, null);
		Person dad = new Person("Parent 2", true, 2, null, null);
		Person gp1 = new Person("GParent 1", true, 3, null, null);
		Person gp2 = new Person("GParent 2", false, 4, null, null);
		Person gp3 = new Person("GParent 3", true, 5, null, null);
		Person gp4 = new Person("GParent 4", false, 6, null, null);
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
		Log.i(TAG, "Created default 7 people");
	}
	
	public static void createTraits(Person person) {
		person.addTrait(new Trait("Hair Color", "Brown"));
		person.addTrait(new Trait("Eye Color", "Blue"));
		person.addTrait(new Trait("Widows Peak", "yes"));
		person.addTrait(new Trait("Connected Earlobes", "no"));
		person.addTrait(new Trait("Cleft Chin", "yes"));
	}

	/**
	 * Gets the singleton instance of the main user. If no user is specified a
	 * default user is created
	 * 
	 * @return The main user
	 */
	public static Person getUser() {
		if (user == null) {
			user = new Person("Default User", true, 0, null, null);
		}
		return user;
	}

	private void setUser(Person user) {
		MainActivity.user = user;
	}

	public void connectToUser(View view) {
		// TODO Adam: Put Code to connect to other user in here
		// TODO Everyone - FYI
		/*
		 * If you need to start a new activity (called ExampleActivity here)
		 * then insert the following code:
		 * 
		 * Intent intent = new Intent(this, ExampleActivity.class);
		 * startActivity(intent);
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
