package com.bdiplus.task.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdiplus.task.model.Task;
import com.bdiplus.task.repository.TaskRepository;
import com.bdiplus.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository repository;

	@Override
	public List<Task> getAllTasks() {
		List<Task> tasks = repository.findAll();
		return tasks;
	}

	@Override
	public Task getTaskById(Long id) {

		Optional<Task> findById = repository.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public Task saveTask(Task task) {
		Task addTask = repository.save(task);

		if (getTaskById(addTask.getId()) != null) {
			return addTask;
		}
		return null;
	}

	@Override
	public Task updateTask(Task task) {

		Optional<Task> findById = repository.findById(task.getId());
		if (findById.isPresent()) {
			repository.save(task);
			return task;
		}
		return null;
	}

	@Override
	public String deletTaskById(Long id) {

		Optional<Task> task = repository.findById(id);
		if (task.isPresent()) {
			repository.deleteById(id);
			return "success";
		}
		return "Error";
	}

	

}
