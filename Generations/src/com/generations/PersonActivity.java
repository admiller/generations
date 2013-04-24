package com.generations;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

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
		EditText name = (EditText)findViewById(R.id.personName);
		Spinner hcSpinner = (Spinner)findViewById(R.id.spinnerHairColor);
		Spinner ecSpinner = (Spinner)findViewById(R.id.spinnerEyeColor);
		Spinner wpSpinner = (Spinner)findViewById(R.id.spinnerWidowsPeak);
		Spinner ceSpinner = (Spinner)findViewById(R.id.spinnerConnectedEarlobes);
		Spinner ccSpinner = (Spinner)findViewById(R.id.spinnerCleftChin);
		if(person != null) {
			String hc = person.getTrait("Hair Color").getTrait();
			String ec = person.getTrait("Eye Color").getTrait();
			String wp = person.getTrait("Widows Peak").getTrait();
			String ce = person.getTrait("Connected Earlobes").getTrait();
			String cc = person.getTrait("Cleft Chin").getTrait();
			
			int hcIndex = 0;
			int ecIndex = 0;
			int wpIndex = 0;
			int ceIndex = 0;
			int ccIndex = 0;
			for(int i = 0; i < Trait.hairColor.length; i++) {
				if(Trait.hairColor[i].equalsIgnoreCase(hc)) {
					hcIndex = i;
					break;
				}
			}
			
			for(int i = 0; i < Trait.eyeColor.length; i++) {
				if(Trait.eyeColor[i].equalsIgnoreCase(ec)) {
					ecIndex = i;
					break;
				}
			}
			
			for(int i = 0; i < Trait.widowsPeak.length; i++) {
				if(Trait.widowsPeak[i].equalsIgnoreCase(wp)) {
					wpIndex = i;
					break;
				}
			}
			
			for(int i = 0; i < Trait.connectedEarlobes.length; i++) {
				if(Trait.connectedEarlobes[i].equalsIgnoreCase(ce)) {
					ceIndex = i;
					break;
				}
			}
			
			for(int i = 0; i < Trait.cleftChin.length; i++) {
				if(Trait.cleftChin[i].equalsIgnoreCase(cc)) {
					ccIndex = i;
					break;
				}
			}
			
			name.setText(person.getName());
			hcSpinner.setSelection(hcIndex);
			ecSpinner.setSelection(ecIndex);
			wpSpinner.setSelection(wpIndex);
			ceSpinner.setSelection(ceIndex);
			ccSpinner.setSelection(ccIndex);
		}
	}
	
	public void savePerson(View view) {
		Person person = TreeActivity.getCurrentPerson();
		if(person != null) {
			EditText name = (EditText)findViewById(R.id.personName);
			Spinner hcSpinner = (Spinner)findViewById(R.id.spinnerHairColor);
			Spinner ecSpinner = (Spinner)findViewById(R.id.spinnerEyeColor);
			Spinner wpSpinner = (Spinner)findViewById(R.id.spinnerWidowsPeak);
			Spinner ceSpinner = (Spinner)findViewById(R.id.spinnerConnectedEarlobes);
			Spinner ccSpinner = (Spinner)findViewById(R.id.spinnerCleftChin);
			
			String hc = hcSpinner.getSelectedItem().toString();
			String ec = ecSpinner.getSelectedItem().toString();
			String wp = wpSpinner.getSelectedItem().toString();
			String ce = ceSpinner.getSelectedItem().toString();
			String cc = ccSpinner.getSelectedItem().toString();
			
			person.setName(name.getText().toString());
			person.getTrait("Hair Color").setTrait(hc);
			person.getTrait("Eye Color").setTrait(ec);
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

