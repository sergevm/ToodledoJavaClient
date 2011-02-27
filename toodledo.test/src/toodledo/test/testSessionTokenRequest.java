package toodledo.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.RestClient;
import com.domaindriven.toodledo.RestClientFactory;
import com.domaindriven.toodledo.Session;
import com.domaindriven.toodledo.SessionTokenRequest;
import com.domaindriven.toodledo.ToodledoSession;

public class testSessionTokenRequest {
	
	Session session;
	RestClient client;
	ToodledoSession.Log log;
	RestClientFactory factory;
	SessionTokenRequest request;
	
	@Before
	public void before() {
		client = mock(RestClient.class);
		session = mock(Session.class);
		factory = mock(RestClientFactory.class);
		when(factory.create(any(String.class))).thenReturn(client);
		request = new SessionTokenRequest("serge", log, factory);
	}
	
	@Test
	public void when_executed_then_a_restclient_is_created() throws Exception {
		request.execute();
		verify(factory).create(any(String.class));
	}
	
	@Test
	public void when_executed_then_the_client_executes() throws Exception {
		request.execute();
		verify(client).Execute(com.domaindriven.toodledo.RequestMethod.GET);
	}
}
