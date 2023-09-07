package com.bdiplus.task;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bdiplus.task.model.Task;
import com.bdiplus.task.restcontroller.TaskRestController;
import com.bdiplus.task.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TaskRestController.class)
public class TaskRestControllerTest {

	static Task task;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskService taskService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		task = new Task();
	}

	@BeforeEach
	void setUp() throws Exception {
		task.setId(1L);
		task.setTaskName("Write Junit Test Case");
		task.setTaskDescription("Write Unit test case for the application  to ensure API is working as expected");
		task.setStatus("Incomplete");
	}

	@Test
	public void testGetTaskById() throws Exception {

		when(taskService.getTaskById(1L)).thenReturn(task);

		mockMvc.perform(get("/api/task/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.taskName", is("Write Junit Test Case"))).andExpect(jsonPath("$.taskDescription",
						is("Write Unit test case for the application  to ensure API is working as expected")));

		verify(taskService, times(1)).getTaskById(1L);
	}

	@Test
	public void testAddTask() throws Exception {

		when(taskService.saveTask(ArgumentMatchers.any())).thenReturn(task);

		ObjectMapper mapper = new ObjectMapper();
		String taskJson = mapper.writeValueAsString(mapper);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/task/save")
				.contentType(MediaType.APPLICATION_JSON).content(taskJson);

		// send request to rest api
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();

		assertEquals(200, status);
	}

	@Test
	public void testDeleteTask() throws Exception {

		when(taskService.saveTask(ArgumentMatchers.any())).thenReturn(task);
		when(taskService.deletTaskById(1L)).thenReturn("success");

		Long id = 1L;

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/task/" + id);

		// send request to rest api
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();

		assertEquals(200, status);
	}

	@Test
	public void testUpdateTask() throws Exception{
		when(taskService.saveTask(task)).thenReturn(task);
		
		task.setId(1L);
		task.setTaskName("Write Junit Test Case");
		task.setTaskDescription("Write Unit test case for the application  to ensure API is working as expected");
		task.setStatus("Completed");
		
		when(taskService.updateTask(task)).thenReturn(task);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/task/update");

		// send request to rest api
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();

		assertEquals(200, status);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		task = null;
	}

}
