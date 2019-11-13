package com.test.springboot.todolist;

import java.net.URI;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Alekhya Ejjina
 * @date 13/11/2019
 */
@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	public static Logger log = Logger.getLogger(TaskController.class);

	/*
	 * This method returns the list of all available tasks
	 */
	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		log.debug("Entered getAllTasks method");
		return taskService.getAllTasks();
	}

	/*
	 * This method returns the requested task
	 * input - id
	 */
	@GetMapping("/tasks/{id}")
	public Task getTask(@PathVariable long id) {
		log.debug("Entered getTask method");
		return taskService.getTask(id);
	}
	
	/*
	 * This method returns the requested task based on description
	 * input - description
	 */
	@GetMapping("/taskByDesc/{description}")
	public Task getTaskByDesc(@PathVariable String description) {
		log.debug("Entered getTaskByDesc method");
		return taskService.getTaskByDesc(description);
	}

	/*
	 * This method deletes the given task
	 * input - id
	 */
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable long id) {
		log.debug("Entered deleteTask method");
		Task task = taskService.deleteTask(id);

		if (task != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	/*
	 * This method marks the given task as 'complete'
	 * input - id
	 */
	@PutMapping("/completeTask/{id}")
	public ResponseEntity<Task> completeTask(@PathVariable long id) {
		log.debug("Entered completeTask method");
		Task taskCompleted =  taskService.completeTask(id);

		return new ResponseEntity<Task>(taskCompleted, HttpStatus.OK);
	}
	
	/*
	 * This method updates the given task
	 * inputs - id, task to be updated
	 */
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task task) {
		log.debug("Entered updateTask method");
		Task taskUpdated = taskService.save(task);
		return new ResponseEntity<Task>(taskUpdated, HttpStatus.OK);
	}

	/*
	 * This method creates the given task
	 * input - task to be created
	 */
	@PostMapping("/tasks")
	public ResponseEntity<Void> createTask(@RequestBody Task task) {
		log.debug("Entered createTask method");
		Task createdCourse = taskService.save(task);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}