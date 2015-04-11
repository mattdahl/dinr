package edu.pomona.dinr;

import java.util.Map;

import com.parse.*;

import org.json.*;
public class ParseInterface {

	
	private static String capitalize(String input) {
		return Character.toUpperCase(input.charAt(0)) + input.substring(1);
	}
	
	/**
	 * 
	 * 
	 * @param meal MUST BE "breakfast", "lunch", or "dinner".  
	 * @param breakfastChoices choices of dining halls;
	 * @return
	 */
	public static Student[] getMatches(String meal, String[] breakfastChoices) {
		
		try {
			JSONObject object = new JSONObject();
			object.put(capitalize(meal).concat("Halls"), breakfastChoices);
		    ParseCloud.callFunctionInBackground(meal, null, new FunctionCallback< Map<String, Object> >() {
		        public void done(Map<String, Object> mapObject, ParseException e) {
		          if (e == null){   
		            
		          } 
		        }
		    });
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Student[0];
		}
		return new Student[0];
	}
	
}
