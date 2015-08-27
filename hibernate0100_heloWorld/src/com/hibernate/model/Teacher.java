package com.hibernate.model;

import javax.persistence.Id;
//@Entity说明该类是一个实体类
@javax.persistence.Entity
public class Teacher {
	private int id;
	private String name;
	private String title;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", title=" + title
				+ "]";
	}
	
	
}
