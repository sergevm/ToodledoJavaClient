package toodledo.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.Request;
import com.domaindriven.toodledo.Session;
import com.domaindriven.toodledo.SessionTokenResponse;
import com.domaindriven.toodledo.ToodledoSession;


public class testSessionTokenResponse {
	long date;
	Session session;
	Request request;
	ToodledoSession.Log log;
	SessionTokenResponse response;
	
	@Before 
	public void setup() throws Exception
	{
		date = Calendar.getInstance().getTimeInMillis();
		session = mock(Session.class);
		request = mock(Request.class);
		when(request.execute()).thenReturn("{\"token\":\"1a2b3c4d5e6f7\"}");

		log = mock(ToodledoSession.Log.class);
		response = new SessionTokenResponse(log, request);
	}
	
	@Test
	public void then_response_can_be_parsed() throws Exception {
		String token = response.parse();	
		assertEquals("1a2b3c4d5e6f7", token);
	}
}
