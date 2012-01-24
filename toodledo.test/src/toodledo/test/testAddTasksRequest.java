package toodledo.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.AddTasksRequest;
import com.domaindriven.toodledo.RestClient;
import com.domaindriven.toodledo.RestClientFactory;
import com.domaindriven.toodledo.Session;
import com.domaindriven.toodledo.Task;

import static org.mockito.Mockito.*;

public class testAddTasksRequest {

	Session session;
	RestClient client;
	AddTasksRequest request;
	RestClientFactory factory;
	
	List<Task> tasks;
	
	long date;
	
	final static String JSON = "[{\"title\":\"title 1\",\"completed\":0,\"note\":\"note 1\"},{\"title\":\"title 2\",\"completed\":0}]";
	
	@Before
	public void before() {
		session = mock(Session.class);
		client = mock(RestClient.class);
		factory = mock(RestClientFactory.class);
		
		when(session.getKey()).thenReturn("mykey");
		when(factory.create(any(String.class))).thenReturn(client);
		
		date = Calendar.getInstance().getTimeInMillis();
		
		tasks = new ArrayList<Task>();

		Task task = new Task();
		task.setTitle("title 1");
		task.setNote("note 1");

		tasks.add(task);
		
		task = new Task();
		task.setTitle("title 2");

		tasks.add(task);
		
		request = new AddTasksRequest(session, tasks, factory);
	}
	
	@Test
	public void when_executing_then_the_task_is_serialized_to_json() throws Exception {
		request.execute();
		verify(client).AddParam("tasks", JSON);
	}
	
	@Test
	public void when_executing_then_the_url_is_correctly_formatted() throws Exception {
		request.execute();
		verify(factory).create("http://api.toodledo.com/2/tasks/add.php?key=mykey");
	}
}
