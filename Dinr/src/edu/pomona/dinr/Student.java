package edu.pomona.dinr;

public final class Student {
	protected final String id;
	protected final String name;
	protected final String major;
	protected final String[] breakfast;
	protected final String[] lunch;
	protected final String[] dinner;
	protected final String college;
	protected final String year;
	protected final String interests;
	
	public Student(String id, String name, String major, 
			String[] breakfast, String[] lunch, String[] dinner,
			String college, String year, String interests) {
		this.id = id;
		this.name = name;
		this.major = major;
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
		this.college = college;
		this.year = year;
		this.interests = interests;
	}
	
	public static Student getInstance(String id, String name, String major, 
			String[] breakfast, String[] lunch, String[] dinner,
			String college, String year, String interests) {
		return new Student(id, name, major, 
			breakfast, lunch, dinner,
			college, year, interests);
	}
	
}
