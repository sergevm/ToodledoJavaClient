package toodledo.test;

import static org.junit.Assert.assertEquals;
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
	
	RestClientFactory clientFactory;
	AccountRequest request;
	RestClient client;
	Session session;
	
	final static String KEY = "abcde123456789";
	
	final static String CLIENTRESPONSE = "{'userid':'a1b2c3d4e5f6','alias':'John','pro':'0','dateformat':'0','timezone':'-6'," +
							"'hidemonths':'2','hotlistpriority':'3','hotlistduedate':'2','showtabnums':'1'," +
							"'lastedit_folder':'1281457337','lastedit_context':'1281457997','lastedit_goal':'1280441959'," +
							"'lastedit_location':'1280441959','lastedit_task':'1281458832','lastdelete_task':'1280898329'," +
							"'lastedit_notebook':'1280894728','lastdelete_notebook':'1280898329'}";
	
	@Before
	public void setup() {
		
		session = mock(Session.class);
		when(session.getKey()).thenReturn(KEY);
		
		client = mock(RestClient.class);
		when(client.getResponse()).thenReturn(CLIENTRESPONSE);
		
		clientFactory = mock(RestClientFactory.class);
		when(clientFactory.create(any(String.class))).thenReturn(client);
		
		request = new AccountRequest(session, clientFactory);
	}
	
	@Test public void When_request_executed_then_correct_url_is_used() throws Exception {
		request.execute();
		verify(clientFactory).create(String.format(AccountRequest.URL_TEMPLATE, KEY));
	}
	
	@Test public void When_request_executed_then_client_response_is_returned_correctly() throws Exception {
		String response = request.execute();
		assertEquals(CLIENTRESPONSE, response);
	}
}
