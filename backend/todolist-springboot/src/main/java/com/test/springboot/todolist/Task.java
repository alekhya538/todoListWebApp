package com.test.springboot.todolist;

/**
 * @author Alekhya Ejjina
 * @date 13/11/2019
 */
public class Task {
	
	private Long id;
	private String description;
	private String status;

	public Task() {

	}

	public Task(long id, String description, String status) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}