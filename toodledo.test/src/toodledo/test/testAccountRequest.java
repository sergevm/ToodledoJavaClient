package toodledo.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.AccountRequest;
import com.domaindriven.toodledo.RestClient;
import com.domaindriven.toodledo.RestClientFactory;
import com.domaindriven.toodledo.Session;

public class testAccountRequest {
	
	Session session;
	AccountRequest request;
	RestClient client;
	RestClientFactory factory;
	
	@Before
	public void before() {
		session = mock(Session.class);
		client = mock(RestClient.class);
		factory = mock(RestClientFactory.class);
		when(factory.create(any(String.class))).thenReturn(client);
		
		request = new AccountRequest(session, factory);
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
