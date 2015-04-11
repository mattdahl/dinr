package edu.pomona.dinr;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.parse.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends ActionBarActivity {
	CallbackManager callbackManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Parse setup
		Parse.enableLocalDatastore(this); 
		Parse.initialize(this, "KiNCYv9wB1ehPSx3oFcyOZJppUihboq1FGbjfhMr", "Vtz801AypDVOmKcbURKigQo6Nr3zg3H8nliZXDbv");
		
		FacebookSdk.sdkInitialize(getApplicationContext());
		setContentView(R.layout.activity_start);
		
		SharedPreferences pref = getSharedPreferences("setup", Context.MODE_PRIVATE);
		if (pref.getBoolean("setup_complete", false)) {
			moveToNextScreen(true);
		} else {
			// do the stupid facebook crap
		}
		
		callbackManager = CallbackManager.Factory.create();

		LoginManager.getInstance().registerCallback(callbackManager,
				new FacebookCallback<LoginResult>() {
			@Override
			public void onSuccess(LoginResult loginResult) {
				AccessToken accessToken = loginResult.getAccessToken();
				
				GraphRequest request = GraphRequest.newMeRequest(
				        accessToken,
				        new GraphRequest.GraphJSONObjectCallback() {
				            @Override
				            public void onCompleted(JSONObject object, GraphResponse response) {
								ParseObject newStudent = new ParseObject("Student");
								try {
									newStudent.put("id", object.getString("id"));
									newStudent.put("name", object.getString("name"));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								newStudent.saveInBackground();
				            }
				        });
				Bundle parameters = new Bundle();
				parameters.putString("fields", "id,name");
				request.setParameters(parameters);
				request.executeAsync();
			}

			@Override
			public void onCancel() {
			}

			@Override
			public void onError(FacebookException exception) {
			}
		});
		
		Button skipbtn = (Button) findViewById(R.id.skip_button);
		skipbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				moveToNextScreen(false);
			}
		});
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    callbackManager.onActivityResult(requestCode, resultCode, data);
	}
	
	private void moveToNextScreen(boolean setupComplete) {
		if (setupComplete) {
			Intent intent = new Intent(this, MainActivity.class);
			this.startActivity(intent);
		} else {
			Intent intent = new Intent(this, SupplementaryDataActivity.class);
			this.startActivity(intent);
		}
	}
}

