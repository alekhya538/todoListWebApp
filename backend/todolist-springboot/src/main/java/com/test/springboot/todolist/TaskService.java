package com.test.springboot.todolist;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Alekhya Ejjina
 * @date 13/11/2019
 */
@Service
public class TaskService {

	public static Logger log = Logger.getLogger(TaskService.class);
	
	private static List<Task> tasks = new ArrayList<>();
	
	//Unique Counter
	private static long id = 0;

	//Hard Coding the data for now. Ideally from a database.
	static {
		tasks.add(new Task(++id, "Sign the building contracts and email to Conveyancer", "complete"));
		tasks.add(new Task(++id, "Renew or Cancel the YuppTv subscription before 30/Nov/2019", "todo"));
		tasks.add(new Task(++id, "Master Microservices with Spring Boot and Spring Cloud", "complete"));
		tasks.add(new Task(++id, "Deploy Spring Boot Microservices to Cloud with Docker and Kubernetes", "todo"));
		tasks.add(new Task(++id, "Call RACV to find out about Building & Construction Insurance", "todo"));
		tasks.add(new Task(++id, "Upgrade CNMS-NG to 6.0 package", "todo"));
		tasks.add(new Task(++id, "Schedule Functional review meeting with Dev/QA/Sterling teams by 15th (Fri) Dec 2019", "todo"));
		tasks.add(new Task(++id, "Complete Employee Directory application using Springboot and ReactJS", "complete"));
		tasks.add(new Task(++id, "Register Online for Heathdale Christian college", "complete"));
		tasks.add(new Task(++id, "Follow-up with QA to check the bug status and plan for fixing before end of Nov", "todo"));
		tasks.add(new Task(++id, "Book Doctor Appointment for vaccinations on 18th Nov", "todo"));
		tasks.add(new Task(++id, "Create Invitation card for House warming on 12/Dec/2019", "todo"));
		tasks.add(new Task(++id, "Tableau License expiring on 30 Nov. Check for new license.", "todo"));
		tasks.add(new Task(++id, "Install Ubuntu on .198 for CDEM deployment", "complete"));
	}

	public List<Task> getAllTasks() {
		log.debug("Entered getAllTasks method");
		return tasks;
	}

	/*
	 * Create/Update task
	 */
	public Task save(Task task) {
		log.debug("Entered save method");
		task.setStatus("todo");
		if (task.getId() == -1 || task.getId() == 0) {
			task.setId(++id);
			tasks.add(task);
		} else {
			deleteTask(task.getId());
			tasks.add(task);
		}
		return task;
	}
	
	/* 
	 * Complete task
	 */
	public Task completeTask(long id) {
		log.debug("Entered completeTask method");
		for (Task task : tasks) {
			if (task.getId() == id) {
				task.setStatus("complete");
				return task;
			}
		}
		return null;
	}

	/*
	 * Delete task
	 */
	public Task deleteTask(long id) {
		log.debug("Entered deleteTask method");
		Task Task = getTask(id);

		if (Task == null)
			return null;

		if (tasks.remove(Task)) {
			return Task;
		}

		return null;
	}

	/*
	 * Get task based on id
	 */
	public Task getTask(long id) {
		log.debug("Entered getTask method");
		for (Task Task : tasks) {
			if (Task.getId() == id) {
				return Task;
			}
		}
		return null;
	}
	
	/*
	 * Get task based on description
	 */
	public Task getTaskByDesc(String description) {
		log.debug("Entered getTaskByDesc method");
		for (Task Task : tasks) {
			if (Task.getDescription() == description) {
				return Task;
			}
		}
		return null;
	}
}