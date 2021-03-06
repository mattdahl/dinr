package edu.pomona.dinr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;







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
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("diningHalls", Arrays.asList(breakfastChoices));
		try {


			ArrayList<ParseObject> object = ParseCloud.callFunction(meal, map);

			System.out.println("Continuing " + object.size());
			for (ParseObject obj: object) {
				System.out.println("!!! => " + obj.toString());
			}
			int idx = 0;
			students = new Student[object.size()];
			for (ParseObject elt : object) {
				
				ArrayList<String> bA = ((ArrayList<String>) elt.get("BreakfastHalls"));
				ArrayList<String> bL = ((ArrayList<String>) elt.get("LunchHalls"));
				ArrayList<String> bD = ((ArrayList<String>) elt.get("DinnerHalls"));
				String[] b = (bA == null) ? new String[0] : new String[bA.size()];
				String[] l = (bL == null) ? new String[0] :new String[bL.size()];
				String[] d = (bD == null) ? new String[0] :new String[bD.size()];
				students[idx] = new Student(elt.getString("objectId"), elt.getString("name"), elt.getString("major"),
						(bA == null) ? new String[0] : bA.toArray(b), (bL == null) ? new String[0] :bL.toArray(l), (bD == null) ? new String[0] :bD.toArray(d),
						elt.getString("college"), elt.getString("year"), elt.getString("interests"), elt.getString("pic_url"));
				idx = idx + 1;

			}

		} catch (Exception e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		System.out.println("returning");
		return (students == null) ? new Student[0] : students;
	}

}
