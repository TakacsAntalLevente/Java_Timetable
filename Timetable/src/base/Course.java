package base;

public class Course {
	
	public String course;
	public String day;
	public String startTime;
	public String endTime;
	public String room;
	
	
	public Course(String course, String day, String startTime, String endTime, String room) {
		this.course = course;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.room = room;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}
	
	
	

}
