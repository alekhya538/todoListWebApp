package com.test.springboot.todolist;

import javax.validation.constraints.AssertTrue;

import org.jboss.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Alekhya Ejjina
 * @date 13/11/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootReactApplicationTests {
	
	@Autowired
	private TaskService taskService;
	
	public static Logger log = Logger.getLogger(SpringBootReactApplicationTests.class);

	@Test
	public void addTask() {
		log.debug("Test addTask()");
		
		//New Task
		Task task = new Task();
		String desc = "Test Create method() in todoList App";
		task.setDescription(desc);
		task.setId((long) -1);
		
		Task createdTask = taskService.save(task);

		assertNotNull(createdTask);
		assertNotNull(createdTask.getId());
		assertEquals(desc, createdTask.getDescription());
	}
	
	@Test
	public void updateTask() {
		log.debug("Test updateTask()");
		
		Task taskToUpdate = taskService.getTask(10);
		
		String descUpdated = "Follow-up with QA to check the bug status and plan for fixing before end of Dec";
		taskToUpdate.setDescription(descUpdated);
		
		Task updatedTask = taskService.save(taskToUpdate);
		
		assertNotNull(updatedTask);
		assertNotNull(updatedTask.getId());
		assertEquals(descUpdated, updatedTask.getDescription());
	}
	
	@Test
	public void deleteTask() {
		log.debug("Test deleteTask()");
		
		//Delete Task #15
		taskService.deleteTask(15);
		
		assertNull(taskService.getTask(15));
	}
	
	@Test
	public void completeTask() {
		log.debug("Test completeTask()");
		
		Task completeTask = taskService.completeTask(4);
		
		assertEquals("complete", completeTask.getStatus());
		
	}

}
