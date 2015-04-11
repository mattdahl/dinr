package edu.pomona.dinr;

import java.util.Map;





import com.parse.*;
import com.parse.ParseException;

import org.json.*;
import org.json.simple.parser.*;
public class ParseInterface {

	
	public static final String DINNER = "dinner";
	public static final String LUNCH = "lunch";
	public static final String BREAKFAST = "breakfast";
	public static final String BRUNCH = "brunch";
	
	private static String capitalize(String input) {
		return Character.toUpperCase(input.charAt(0)) + input.substring(1);
	}
	
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param major
	 * @param breakfast
	 * @param lunch
	 * @param dinner
	 * @param college
	 * @param year
	 * @param interests
	 */
	public static void pushStudent(String id, String name, String major,
			String[] breakfast, String[] lunch, String[] dinner, 
			String college, String year, String interests, String pic_url) {
		ParseObject newStudent = new ParseObject("Student");
		newStudent.put("objectId", id);
		newStudent.put("id", id);
		newStudent.put("name", name);
		newStudent.put("major", major);
		newStudent.put("BreakfastHalls", breakfast);
		newStudent.put("LunchHalls", lunch);
		newStudent.put("DinnerHalls", dinner);
		newStudent.put("college", college);
		newStudent.put("year", year);
		newStudent.put("interests", interests);
		newStudent.put("pic_url", pic_url);
		newStudent.saveInBackground();
	}
	
	/**
	 * 
	 * @param id
	 * @param breakfast
	 * @param lunch
	 * @param dinner
	 */
	public static void changeStudent(String id, final String[] breakfast, final String[] lunch, final String[] dinner) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
		query.getInBackground(id, new GetCallback<ParseObject>() {
		  public void done(ParseObject object, ParseException e) {
		    if (e == null) {
		    	object.put("BreakfastHalls", breakfast);
		    	object.put("LunchHalls", lunch);
		    	object.put("DinnerHalls", dinner);
		    	object.saveInBackground();
		    } else {
		      // something went wrong
		    }
		  }
		});
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
									elt.getString("college"), elt.getString("year"), elt.getString("interests"), elt.getString("pic_url"));
									
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
