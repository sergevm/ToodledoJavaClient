package toodledo.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.DeleteTasksResponse;
import com.domaindriven.toodledo.Request;
import com.domaindriven.toodledo.Response;
import com.domaindriven.toodledo.Session;

public class testDeleteTasksResponse {

	private static final String ERRORJSON = "{\"errorCode\":1,\"errorDesc\":\"Empty key\"}";
	private final static String JSON = "[{\"id\":\"1\"},{\"id\":\"2\"}]";
	private final static String EMPTYJSON = "";
	
	Request request;
	Session session;
	Response<List<String>> response;
	
	@Before
	public void before() throws Exception {
		session = mock(Session.class);
		request = mock(Request.class);
		response = new DeleteTasksResponse(session, request);
	}
	
	@Test
	public void then_response_can_be_parsed() throws Exception {

		when(request.execute()).thenReturn(JSON);

		List<String> taskIds = response.parse();
		
		assertEquals(2, taskIds.size());
		assertEquals("1", taskIds.get(0));
		assertEquals("2", taskIds.get(1));
	}
	
	@Test
	public void then_empty_response_can_be_parsed() throws Exception {
		
		when(request.execute()).thenReturn(EMPTYJSON);
		
		List<String> taskIds = response.parse();
		
		assertEquals(0, taskIds.size());
	}
	
	@Test
	public void then_error_message_returned_is_handled() throws Exception {
		when(request.execute()).thenReturn(ERRORJSON);
		
		List<String> taskIds = response.parse();
		
		assertEquals(0, taskIds.size());
		verify(session).Log(DeleteTasksResponse.TAG, ERRORJSON);
	}
}
