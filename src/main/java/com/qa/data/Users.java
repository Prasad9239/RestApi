package com.qa.data;

//pojo - plain old java object
public class Users {
	String User;
	String Job;
	String Id;
	String CreatedAt;
	
	public Users(){
		
	}
	
	public Users(String User,String Job){
		this.User=User;
		this.Job=Job;
	}
	
	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getJob() {
		return Job;
	}

	public void setJob(String job) {
		Job = job;
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(String createdAt) {
		CreatedAt = createdAt;
	}
	
	

}
