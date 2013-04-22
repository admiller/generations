package com.generations;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TreeActivity extends Activity {
	
	private static Person currentPerson;

	//TODO Austin : Insert code to display the tree
	public void buildTree()  {
		Person user = MainActivity.getUser();
		Button userButton = (Button)findViewById(R.id.userButton);
		Button p1Button = (Button)findViewById(R.id.parent1Button);
		Button p2Button = (Button)findViewById(R.id.parent2Button);
		Button gp1Button = (Button)findViewById(R.id.gparent1Button);
		Button gp2Button = (Button)findViewById(R.id.gparent2Button);
		Button gp3Button = (Button)findViewById(R.id.gparent3Button);
		Button gp4Button = (Button)findViewById(R.id.gparent4Button);
		userButton.setText(user.getName());
		p1Button.setText(user.getParent(1).getName());
		p2Button.setText(user.getParent(2).getName());
		gp1Button.setText(user.getParent(1).getParent(1).getName());
		gp2Button.setText(user.getParent(1).getParent(2).getName());
		gp3Button.setText(user.getParent(2).getParent(1).getName());
		gp4Button.setText(user.getParent(2).getParent(2).getName());
		
	}
	
	public void saveTree(View view) {
		//TODO Kriti: Insert code to save the tree to the SQL database
	}
	
	public void goBack(View view) {
		finish();
	}
	
	public void userClicked(View view) {
		currentPerson = MainActivity.getUser();
		startPersonActivity();
	}
	
	public void p1Clicked(View view) {
		currentPerson = MainActivity.getUser().getParent(1);
		startPersonActivity();
	}
	
	public void p2Clicked(View view) {
		currentPerson = MainActivity.getUser().getParent(2);
		startPersonActivity();
	}
	
	public void gp1Clicked(View view) {
		currentPerson = MainActivity.getUser().getParent(1).getParent(1);
		startPersonActivity();
	}
	
	public void gp2Clicked(View view) {
		currentPerson = MainActivity.getUser().getParent(1).getParent(2);
		startPersonActivity();
	}
	
	public void gp3Clicked(View view) {
		currentPerson = MainActivity.getUser().getParent(2).getParent(1);
		startPersonActivity();
	}
	
	public void gp4Clicked(View view) {
		currentPerson = MainActivity.getUser().getParent(2).getParent(2);
		startPersonActivity();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_tree);
		// Show the Up button in the action bar.
		setupActionBar();
		buildTree();
	}
	
	public void startPersonActivity() {
		Intent intent = new Intent(this, PersonActivity.class);
		startActivity(intent);
	}
	
	public static Person getCurrentPerson() {
		return currentPerson;
	}
	

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	public void onResume() {
		super.onResume();
		buildTree();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main , menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
