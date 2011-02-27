package toodledo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.Request;
import com.domaindriven.toodledo.Session;
import com.domaindriven.toodledo.Task;
import com.domaindriven.toodledo.UpdateTasksResponse;


public class testUpdateTasksResponse {

	final static String EMPTYJSON = "";
	final static String ERRORJSON = "{\"errorCode\":1,\"errorDesc\":\"Empty key\"}";
	final static String JSON = "[{\"id\":\"1\",\"title\":\"Title 1\",\"modified\":%d},{\"id\":\"2\",\"title\":\"Title 2\",\"modified\":%d, \"completed\":1}]";

	long date;
	Request request;
	Session session;
	UpdateTasksResponse response;
	
	@Before
	public void before() throws Exception {
		session = mock(Session.class);
		request = mock(Request.class);
		date = Calendar.getInstance().getTimeInMillis();		
		response = new UpdateTasksResponse(session, request);
	}
	
	@Test
	public void then_response_can_be_parsed() throws Exception {

		when(request.execute()).thenReturn(String.format(JSON, date, date));

		List<Task> tasks = response.parse();
		
		assertEquals(2, tasks.size());
		assertFalse(tasks.get(0).getCompleted());
		assertTrue(tasks.get(1).getCompleted());
	}
	
	@Test
	public void then_empty_response_can_be_parsed() throws Exception {
		
		when(request.execute()).thenReturn(EMPTYJSON);
		
		List<Task> tasks = response.parse();
		
		assertEquals(0, tasks.size());
	}
	
	@Test
	public void then_error_message_returned_is_handled() throws Exception {
		when(request.execute()).thenReturn(ERRORJSON);
		
		List<Task> tasks = response.parse();
		
		assertEquals(0, tasks.size());
		verify(session).Log(UpdateTasksResponse.TAG, ERRORJSON);
	}
}
