package toodledo.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.Account;
import com.domaindriven.toodledo.AccountResponse;
import com.domaindriven.toodledo.Request;
import com.domaindriven.toodledo.Session;


public class testAccountResponse {
	
	Request request;
	Session session;
	AccountResponse response;
	
	final static String KEY = "abcde123456789";
	
	final static String CLIENTRESPONSE = "{'userid':'a1b2c3d4e5f6','alias':'John','pro':'0','dateformat':'0','timezone':'-6'," +
							"'hidemonths':'2','hotlistpriority':'3','hotlistduedate':'2','showtabnums':'1'," +
							"'lastedit_folder':'1281457337','lastedit_context':'1281457997','lastedit_goal':'1280441959'," +
							"'lastedit_location':'1280441959','lastedit_task':'1281458832','lastdelete_task':'1280898329'," +
							"'lastedit_notebook':'1280894728','lastdelete_notebook':'1280898329'}";
	
	@Before
	public void setup() throws Exception {
		
		session = mock(Session.class);
		when(session.getKey()).thenReturn(KEY);
		
		request = mock(Request.class);
		when(request.execute()).thenReturn(CLIENTRESPONSE);
		
		response = new AccountResponse(session, request);
	}
	
	@Test public void When_response_parsed_then_account_is_correctly_parsed() throws JSONException, Exception {
		Account account = response.parse();
		
		assertEquals(1281458832, account.getLastEditTask());
		assertEquals(1280898329, account.getLastDeleteTask());
	}
}
