package toodledo.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.RestClient;
import com.domaindriven.toodledo.RestClientFactory;
import com.domaindriven.toodledo.Session;
import com.domaindriven.toodledo.Task;
import com.domaindriven.toodledo.UpdateTasksRequest;

import static org.mockito.Mockito.*;

public class testUpdateTasksRequest {

	final static String SESSION_KEY = "mykey";
	
	final static String EMPTYJSON = "";
	final static String JSON = "[{\"id\":\"1\",\"title\":\"Title 1\"}]";
	final static String ERRORJSON = "{\"errorCode\":1,\"errorDesc\":\"Empty key\"}";
	
	Session session;
	RestClient client;
	RestClientFactory factory;
	UpdateTasksRequest request;
	
	private List<Task> tasks;
	
	@Before
	public void before() {
		session = mock(Session.class);
		client = mock(RestClient.class);
		factory = mock(RestClientFactory.class);
		
		when(session.getKey()).thenReturn(SESSION_KEY);
		when(factory.create(any(String.class))).thenReturn(client);
	
		tasks = new ArrayList<Task>();
		
		Task task = new Task();
		task.setId("1");
		task.setTitle("Title 1");
		task.setCompleted(true);
		
		tasks.add(task);
		
		request = new UpdateTasksRequest(session, tasks, factory);
	}
	
	@Test
	public void when_executing_then_the_task_is_serialized_to_json() throws Exception {
		request.execute();
		verify(client).AddParam("tasks", JSON);
	}
	
	@Test
	public void when_executing_then_the_url_is_correctly_formatted() throws Exception {
		request.execute();
		verify(factory).create("http://api.toodledo.com/2/tasks/edit.php?key=mykey;");
	}
}
