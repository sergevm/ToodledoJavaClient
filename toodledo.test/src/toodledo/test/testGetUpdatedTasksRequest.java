package toodledo.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.GetUpdatedTasksRequest;
import com.domaindriven.toodledo.RestClient;
import com.domaindriven.toodledo.RestClientFactory;
import com.domaindriven.toodledo.Session;


public class testGetUpdatedTasksRequest {
	final static String SESSION_KEY = "mykey";
	
	long after;
	Session session;
	RestClient client;
	List<String> keys;
	RestClientFactory factory;
	GetUpdatedTasksRequest request;
	
	@Before
	public void before() {
		session = mock(Session.class);
		client = mock(RestClient.class);
		factory = mock(RestClientFactory.class);
		
		after = Calendar.getInstance().getTimeInMillis() / 1000;
		
		when(session.getKey()).thenReturn(SESSION_KEY);
		when(factory.create(any(String.class))).thenReturn(client);
		
		request = new GetUpdatedTasksRequest(session, after, factory);
	}
	
	@Test
	public void when_executed_then_correct_url_is_used() throws Exception {
		request.execute();
		verify(factory).create(String.format(GetUpdatedTasksRequest.URL_TEMPLATE, SESSION_KEY, after));
	}
}
