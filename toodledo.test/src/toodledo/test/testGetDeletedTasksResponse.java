package toodledo.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.GetDeletedTasksResponse;
import com.domaindriven.toodledo.Request;
import com.domaindriven.toodledo.Session;


public class testGetDeletedTasksResponse {

	final static String JSON = "[{\"num\":\"2\"},{\"id\":\"1234\",\"stamp\":\"1234567891\"},{\"id\":\"1235\",\"stamp\":\"1234567892\"}]";	
	private static final String ERRORJSON = "{\"errorCode\":1,\"errorDesc\":\"Empty key\"}";
	private final static String EMPTYJSON = "";
	
	Request request;
	Session session;
	GetDeletedTasksResponse response;

	@Before
	public void before() throws Exception {
		session = mock(Session.class);
		request = mock(Request.class);
		response = new GetDeletedTasksResponse(session, request);
	}
	@Test
	public void then_response_can_be_parsed() throws Exception {

		when(request.execute()).thenReturn(JSON);

		List<String> taskIds = response.parse();
		
		assertEquals(2, taskIds.size());
		assertEquals("1234", taskIds.get(0));
		assertEquals("1235", taskIds.get(1));
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
		verify(session).Log(GetDeletedTasksResponse.TAG, ERRORJSON);
	}
}
