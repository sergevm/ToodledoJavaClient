package toodledo.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.AddTasksResponse;
import com.domaindriven.toodledo.Request;
import com.domaindriven.toodledo.Response;
import com.domaindriven.toodledo.Session;
import com.domaindriven.toodledo.Task;
import com.domaindriven.toodledo.ToodledoSession;

public class testAddTasksResponse {
	
	private static final String JSONTEMPLATE = 
		"[{\"id\":\"x\",\"title\":\"title 1\",\"modified\":%d,\"completed\":0,\"folder\":\"0\"}," + 
		"{\"id\":\"y\",\"title\":\"title 2\",\"modified\":%d,\"completed\":1000,\"folder\":\"0\"}]";
	
	long date;
	Session session;
	Request request;
	ToodledoSession.Log log;
	Response<List<Task>> response;
	
	@Before 
	public void setup() throws Exception
	{
		date = Calendar.getInstance().getTimeInMillis() / 1000;
		session = mock(Session.class);
		request = mock(Request.class);
		when(request.execute()).thenReturn(String.format(JSONTEMPLATE, date, date));

		log = mock(ToodledoSession.Log.class);
		response = new AddTasksResponse(session, request);
	}
	
	@Test
	public void then_response_can_be_parsed() throws Exception {
		List<Task> tasks = response.parse();	
		
		assertEquals(2, tasks.size());
		assertEquals("x", tasks.get(0).getId());
		assertEquals("title 1", tasks.get(0).getTitle());
		assertEquals(date, tasks.get(0).getModified());
		assertEquals(0, tasks.get(0).getCompleted());
		assertEquals(1000, tasks.get(1).getCompleted());
	}
}
