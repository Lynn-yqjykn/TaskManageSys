package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Comparable<Task> {

	Date taskCreateTime = null;
	Date taskToDoTime = null;
	String name = null;
	String describe = null;

	int taskType = 0;
	int taskDelay = 0;
	int taskLevel = 0;
	int taskWeight = 0;

	String picture = null;

	int year = 0;
	int month = 0;
	int day = 0;
	int hour = 0;
	int minute = 0;
	int second = 0;

	public Task(String name, String describe, String picture ,int taskWeight) {

		this.describe = describe;
		this.picture = picture;
		this.name = name;
		this.taskWeight = taskWeight;
		taskCreateTime = new Date();
		taskToDoTime = (Date) taskCreateTime.clone();
		InitorUpdateDate();
	}

	public Task(String name, String describe) {

		taskCreateTime = new Date();
		taskToDoTime = (Date) taskCreateTime.clone();
		this.name = name;
		this.describe = describe;
		InitorUpdateDate();
	}

	public Task() {
		name = "hehe";
		describe = "haha";
		taskCreateTime = new Date(0);
		taskToDoTime = (Date) taskCreateTime.clone();
		InitorUpdateDate();
	}

	@SuppressWarnings("deprecation")
	protected void InitorUpdateDate() {

		year = this.getTaskToDoTime().getYear() + 1900;
		month = this.getTaskToDoTime().getMonth() + 1;
		day = this.getTaskToDoTime().getDay() + 4;
		hour = this.getTaskToDoTime().getHours();
		minute = this.getTaskToDoTime().getMinutes();
		second = this.getTaskToDoTime().getSeconds();
	}

	/**
	 * @param taskType
	 * @param taskDelay
	 * @param taskLevel
	 * @param name
	 * @param taskCreateTime
	 * @param taskToDoTime
	 * @param describe
	 */

	public Task(int taskType, int taskDelay, int taskLevel, String picture,
			String name, String taskCreateTime, String taskToDoTime,
			String describe ,int taskWeight ) {

		this.taskType = taskType;
		this.taskDelay = taskDelay;
		this.taskLevel = taskLevel;
		this.taskCreateTime = new Date(Long.parseLong(taskCreateTime));
		this.taskToDoTime = new Date(Long.parseLong(taskToDoTime));
		this.describe = describe;
		this.picture = picture;
		this.name = name;
		this.taskWeight = taskWeight;
		InitorUpdateDate();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Task) {
			Task temp = (Task) obj;
			if (this.name.equals(temp.name)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String toString() {
		return ""
				+ taskType
				+ " "
				+ taskDelay
				+ " "
				+ taskLevel
				+ " "
				+ name
				+ " "
				+ new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
						.format(taskCreateTime)
				+ " "
				+ new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
						.format(taskToDoTime) + " " + describe + " "
				+ taskCreateTime.getTime() + " " + taskToDoTime.getTime();
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getTaskCreateTime() {
		return taskCreateTime;
	}

	public void setTaskCreateTime(Date taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}

	public Date getTaskToDoTime() {
		return taskToDoTime;
	}

	public void setTaskToDoTime(Date taskToDoTime) {
		this.taskToDoTime = taskToDoTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public int getTaskDelay() {
		return taskDelay;
	}

	public void setTaskDelay(int taskDelay) {
		this.taskDelay = taskDelay;
	}

	public int getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(int taskLevel) {
		this.taskLevel = taskLevel;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getTaskweigh() {
		return taskWeight;
	}

	public void setTaskweigh(int taskWeight) {
		this.taskWeight = taskWeight;
	}

	@Override
	public int compareTo(Task another) {
		return (int) (this.taskToDoTime.getTime() - another.taskToDoTime
				.getTime());

	}


}
