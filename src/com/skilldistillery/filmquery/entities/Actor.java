package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Actor {

	private int id;
	private String fName;
	private String lName;

	public Actor() {
		super();
	}

	public Actor(int id, String fName, String lName) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}


	@Override
	public String toString() {
		return "Actor [fName=" + fName + ", lName=" + lName + ", id=" + id + "]";
	}

	public void setFilms(List<Film> findFilmsByActorId) {
		// TODO Auto-generated method stub
		
	}

	

}
