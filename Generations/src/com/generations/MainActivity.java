package com.generations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	private static Person user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//TODO Kriti: Insert SQL Loading code here that will load previously made entries in the tree
		// Make sure that you set the User and all of their family upon load
		// for now we will just use the following lines
		Person john = new Person("John", true, null, null);
		Person mom = new Person("Jessica", false, null, null);
		Person dad = new Person("Mike", true, null, null);
		john.setParent(1, mom);
		john.setParent(2, dad);
		setUser(user);
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
		//TODO Everyone: 
		/* If you need to start a new activity (called ExampleActivity here)
		 * then insert the following code:
		 * 
		 *	Intent intent = new Intent(this, ExampleActivity.class);
		 *	startActivity(intent);
		 */
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
