package toodledo.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.DeleteTasksRequest;
import com.domaindriven.toodledo.RestClient;
import com.domaindriven.toodledo.RestClientFactory;
import com.domaindriven.toodledo.Session;

import static org.mockito.Mockito.*;

public class testDeleteTasksRequest {

	final static String SESSION_KEY = "mykey";
	final static String JSON = "[\"1\",\"2\"]";	
	
	Session session;
	RestClient client;
	List<String> keys;
	RestClientFactory factory;
	DeleteTasksRequest request;
	
	@Before
	public void before() {
		session = mock(Session.class);
		client = mock(RestClient.class);
		factory = mock(RestClientFactory.class);
		
		when(session.getKey()).thenReturn(SESSION_KEY);
		when(factory.create(any(String.class))).thenReturn(client);
		
		keys = new ArrayList<String>();
		keys.add("1");
		keys.add("2");
		
		request = new DeleteTasksRequest(session, keys, factory);
	}
	
	@Test
	public void when_executed_then_correct_url_is_used() throws Exception {
		request.execute();
		verify(factory).create("http://api.toodledo.com/2/tasks/delete.php?key=mykey");
	}
	
	@Test
	public void when_executed_then_the_task_ids_are_translated_correctly() throws Exception {
		request.execute();
		verify(client).AddParam("tasks", JSON);
	}
}
