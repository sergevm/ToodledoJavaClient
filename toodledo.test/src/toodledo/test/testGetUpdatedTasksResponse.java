package toodledo.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.GetUpdatedTasksResponse;
import com.domaindriven.toodledo.Request;
import com.domaindriven.toodledo.Session;
import com.domaindriven.toodledo.SyncException;
import com.domaindriven.toodledo.Task;


public class testGetUpdatedTasksResponse {
	final static String JSON = "[{\"num\":\"2\"},{\"id\":\"1234\",\"title\":\"Title 1\",\"modified\":\"1234567891\",\"completed\":0," + 
	"\"folder\":\"123\",\"star\":\"0\",\"priority\":\"0\",\"note\":null},{\"id\":\"1235\",\"title\":\"Title 1\",\"modified\":\"1234567891\",\"completed\":0," + 
	"\"folder\":\"345\",\"star\":\"0\",\"priority\":\"0\",\"note\":null}]";	
	
	private static final String ERRORJSON = "{\"errorCode\":1,\"errorDesc\":\"Empty key\"}";
	private final static String EMPTYJSON = "";
	
	Request request;
	Session session;
	GetUpdatedTasksResponse response;

	@Before
	public void before() throws Exception {
		session = mock(Session.class);
		request = mock(Request.class);
		response = new GetUpdatedTasksResponse(session, request);
	}
	@Test
	public void then_response_can_be_parsed() throws Exception {

		when(request.execute()).thenReturn(JSON);

		List<Task> tasks = response.parse();
		
		assertEquals(2, tasks.size());
	}
	
	@Test
	public void then_empty_response_can_be_parsed() throws Exception {
		
		when(request.execute()).thenReturn(EMPTYJSON);
		
		List<Task> tasks = response.parse();
		
		assertEquals(0, tasks.size());
	}
	
	@Test(expected=SyncException.class)
	public void then_error_message_returned_is_handled() throws Exception {

		when(request.execute()).thenReturn(ERRORJSON);		
		response.parse();
	}
}
