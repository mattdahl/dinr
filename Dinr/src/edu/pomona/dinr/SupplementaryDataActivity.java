package edu.pomona.dinr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SupplementaryDataActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_supplementary_data);
		
		this.setTitle("Your Profile");

		Button doneButton = (Button) findViewById(R.id.done_button);
		doneButton.setOnClickListener(listener);
		
		EditText name_label = (EditText) findViewById(R.id.name_edit);
		name_label.setText("Matt Dahl");

		EditText major_label = (EditText) findViewById(R.id.major_edit);
		major_label.setText("Politics");
		
		EditText college_label = (EditText) findViewById(R.id.college_edit);
		college_label.setText("Pomona");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.supplementary_data, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(v.getContext(), MainActivity.class);
			v.getContext().startActivity(intent);
		}
	};
}
