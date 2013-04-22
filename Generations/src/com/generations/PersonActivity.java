package com.generations;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class PersonActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_person);
		// Show the Up button in the action bar.
		setupActionBar();
		loadPerson();
	}
	
	public void loadPerson() {
		Person person = TreeActivity.getCurrentPerson();
		if(person != null) {
			EditText name = (EditText)findViewById(R.id.personName);
			EditText hairColor = (EditText)findViewById(R.id.hairColorTextField);
			EditText eyeColor = (EditText)findViewById(R.id.eyeColorTextField);
			CheckBox wpBox = (CheckBox)findViewById(R.id.widowsPeakBox);
			CheckBox ceBox = (CheckBox)findViewById(R.id.earlobesBox);
			CheckBox ccBox = (CheckBox)findViewById(R.id.cleftChinBox);
			name.setText(person.getName());
			hairColor.setText(person.getTrait("Hair Color").getTrait());
			eyeColor.setText(person.getTrait("Eye Color").getTrait());
			if(person.getTrait("Widows Peak").getTrait().equalsIgnoreCase("yes")) {
				wpBox.setChecked(true);
			} else {
				wpBox.setChecked(false);
			}
			if(person.getTrait("Connected Earlobes").getTrait().equalsIgnoreCase("yes")) {
				ceBox.setChecked(true);
			} else {
				ceBox.setChecked(false);
			}
			if(person.getTrait("Cleft Chin").getTrait().equalsIgnoreCase("yes")) {
				ccBox.setChecked(true);
			} else {
				ccBox.setChecked(false);
			}
		}
	}
	
	public void savePerson(View view) {
		Person person = TreeActivity.getCurrentPerson();
		if(person != null) {
			EditText name = (EditText)findViewById(R.id.personName);
			EditText hairColor = (EditText)findViewById(R.id.hairColorTextField);
			EditText eyeColor = (EditText)findViewById(R.id.eyeColorTextField);
			CheckBox wpBox = (CheckBox)findViewById(R.id.widowsPeakBox);
			CheckBox ceBox = (CheckBox)findViewById(R.id.earlobesBox);
			CheckBox ccBox = (CheckBox)findViewById(R.id.cleftChinBox);
			String wp = wpBox.isChecked() ? "yes" : "no";
			String ce = ceBox.isChecked() ? "yes" : "no";
			String cc = ccBox.isChecked() ? "yes" : "no";
			person.setName(name.getText().toString());
			person.getTrait("Hair Color").setTrait(hairColor.getText().toString());
			person.getTrait("Eye Color").setTrait(eyeColor.getText().toString());
			person.getTrait("Widows Peak").setTrait(wp);
			person.getTrait("Connected Earlobes").setTrait(ce);
			person.getTrait("Cleft Chin").setTrait(cc);
		}
		finish();
	}
	
	public void goBack(View view) {
		finish();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

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
