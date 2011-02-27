package toodledo.test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.domaindriven.toodledo.Account;
import com.google.gson.Gson;


public class testAccountGson {
	Account account;
	long lasteditdate;
	
	@Before
	public void before() {
		lasteditdate = Calendar.getInstance().getTimeInMillis() / 1000;
	}
	
	@Test
	public void test_serialization() {	
		String json = String.format("{\"userid\":\"serge\",\"lastedit_task\":%d,\"lastdelete_task\":%d,\"dateformat\":2}", lasteditdate, lasteditdate);		

		Gson gson = new Gson();
		Account account = gson.fromJson(json, Account.class);
		
		assertEquals("serge", account.getUserId());
		assertEquals(lasteditdate, account.getLastEditTask());
		assertEquals(lasteditdate, account.getLastDeleteTask());
		assertEquals("dd/MM/yyyy", account.getDateFormat());
	}
	
	@Test
	public void test_deserialization() {
		account = new Account("serge");
		account.setLastEditTask(lasteditdate);
		account.setLastDeleteTask(lasteditdate);
		
		Gson gson = new Gson();
		String json = gson.toJson(account);
		assertEquals(String.format("{\"userid\":\"serge\",\"lastedit_task\":%d,\"lastdelete_task\":%d,\"dateformat\":0}", lasteditdate, lasteditdate), json);
	}
}
