package edu.pomona.dinr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends ActionBarActivity {
	CallbackManager callbackManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final SharedPreferences pref = getSharedPreferences("setup", Context.MODE_PRIVATE);
		
		// Parse setup
		Parse.enableLocalDatastore(this); 
		Parse.initialize(this, "KiNCYv9wB1ehPSx3oFcyOZJppUihboq1FGbjfhMr", "Vtz801AypDVOmKcbURKigQo6Nr3zg3H8nliZXDbv");
		
		FacebookSdk.sdkInitialize(getApplicationContext());
		setContentView(R.layout.activity_start);
		
		// Logo
		TextView txt = (TextView) findViewById(R.id.logo);
		Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/chatlet.ttf");
		txt.setTypeface(font);
		
		callbackManager = CallbackManager.Factory.create();

		LoginManager.getInstance().registerCallback(callbackManager,
				new FacebookCallback<LoginResult>() {
			@Override
			public void onSuccess(LoginResult loginResult) {
				AccessToken accessToken = loginResult.getAccessToken();
				
				// Get the user id, name, and pic_url, and save to Parse
				GraphRequest request = GraphRequest.newMeRequest(
				        accessToken,
				        new GraphRequest.GraphJSONObjectCallback() {
				            @Override
				            public void onCompleted(JSONObject object, GraphResponse response) {
								ParseObject newStudent = new ParseObject("Student");
								try {
									newStudent.put("fb_id", object.getString("id"));
									newStudent.put("name", object.getString("name"));
									newStudent.put("pic_url", "http://graph.facebook.com/" +  object.getString("id") + "/picture?type=large"); 
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								newStudent.saveInBackground();
								
								// Advance to the next screen
								moveToNextScreen(pref.getBoolean("setup_complete", false));
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

