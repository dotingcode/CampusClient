package com.campus.prime.ui.app;

public class Score {
	
	private int scoreId;
	private int grade;
	private int semester;
	private int credit;
	private float firstScore;
	private float gpa;
	private float secondScore;
	private String courseName;
	private String courseTeacther;
	private String courseType;
	public int getScoreId() {
		return scoreId;
	}
	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getCredit() {
		return credit;
	}
	public void setFirstScore(float firstScore) {
		this.firstScore = firstScore;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}


	public float getFirstScore() {
		return firstScore;
	}
	public float getGpa() {
		return gpa;
	}
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	public float getSecondScore() {
		return secondScore;
	}
	public void setSecondScore(float secondScore) {
		this.secondScore = secondScore;
	}
	public void setGpa(int gpa) {
		this.gpa = gpa;
	}

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseTeacther() {
		return courseTeacther;
	}
	public void setCourseTeacther(String courseTeacther) {
		this.courseTeacther = courseTeacther;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	
}
