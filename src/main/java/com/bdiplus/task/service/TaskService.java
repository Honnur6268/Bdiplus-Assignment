package com.bdiplus.task.service;

import java.util.List;

import com.bdiplus.task.model.Task;

public interface TaskService {
	List<Task> getAllTasks();

	Task getTaskById(Long id);

	Task saveTask(Task task);

	Task updateTask(Task task);

	String deletTaskById(Long id);

}
