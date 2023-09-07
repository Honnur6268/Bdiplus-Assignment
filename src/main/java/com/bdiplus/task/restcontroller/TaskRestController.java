package com.bdiplus.task.restcontroller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdiplus.task.model.Task;
import com.bdiplus.task.service.TaskService;

@RestController
@RequestMapping("/api/task")
@CrossOrigin("*")
public class TaskRestController {

	@Autowired
	private TaskService service;

	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> allTasks = service.getAllTasks();
		return new ResponseEntity<>(allTasks, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Task> createTask(@RequestBody Task task) {

		Task saveTask = service.saveTask(task);
		if (saveTask != null) {
			return ResponseEntity.ok(saveTask);
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		Task taskById = service.getTaskById(id);
		if (taskById != null) {

			return new ResponseEntity<>(taskById, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable Long id) {

		String status = service.deletTaskById(id);
		if (status == "success") {

			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateTask(@RequestBody Task task) {
		ResponseEntity<Task> taskById = getTaskById(task.getId());
		if (taskById.getStatusCode() == HttpStatus.OK) {

			Task updateTask = service.updateTask(task);
			return new ResponseEntity<>("Updated Successfuly", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error!! ocurred during Updation. Make sure data is correct",
				HttpStatus.NOT_ACCEPTABLE);

	}

}
