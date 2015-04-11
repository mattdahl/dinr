package edu.pomona.dinr;

import java.util.Map;





import com.parse.*;
import com.parse.ParseException;

import org.json.*;
import org.json.simple.parser.*;
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
	private static Student[] students;
	public static Student[] getMatches(String meal, String[] breakfastChoices) {
		final JSONParser parser = new JSONParser();
		
		try {
			JSONObject object = new JSONObject();
			object.put(capitalize(meal).concat("Halls"), breakfastChoices);
		    ParseCloud.callFunctionInBackground(meal, null, new FunctionCallback<String>() {
		        public void done(String object, ParseException e) {
		          if (e == null){   
		            try {
						JSONObject obj = (JSONObject) parser.parse(object);
						JSONArray array = (JSONArray) obj.getJSONArray("result");
						 students = new Student[array.length()];
						for (int i = 0; i < array.length(); i++) {
							JSONObject elt = array.getJSONObject(i);
							students[i] = new Student(elt.getString("objectId"), elt.getString("name"), elt.getString("major"),
									(String[]) elt.get("BreakfastHalls"), (String[]) elt.get("LunchHalls"), (String[]) elt.get("DinnerHalls"),
									elt.getString("college"), elt.getString("year"), elt.getString("interests"));
									
						}
						
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (org.json.simple.parser.ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
		          } 
		        }
		    });
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Student[0];
		}
		return (students == null) ? new Student[0] : students;
	}
	
}
