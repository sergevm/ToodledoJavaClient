package toodledo.test;

import java.util.Calendar;

import org.junit.*;

import com.domaindriven.toodledo.Account;
import com.domaindriven.toodledo.AccountResponse;
import com.domaindriven.toodledo.Request;
import com.domaindriven.toodledo.Session;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class testAccountResponse {

	long date;
	Session session;
	Request request;
	AccountResponse response;
	
	@Before 
	public void setup()
	{
		date = Calendar.getInstance().getTimeInMillis();
		session = mock(Session.class);
		request = mock(Request.class);
		response = new AccountResponse(session, request);
	}
	
	@Test
	public void then_response_can_be_parsed() throws Exception {
		when(request.execute()).thenReturn(String.format("{\"userid\":\"serge\",\"lastedit_task\":%d,\"lastdelete_task\":%d,\"dateformat\":2}", date, date));
		Account account = response.parse();
		
		assertNotNull("account is null", account);
		assertEquals("serge", account.getUserId());
		assertEquals("dd/MM/yyyy", account.getDateFormat());
		assertEquals(date, account.getLastEditTask());
		assertEquals(date, account.getLastDeleteTask());
	}
}
