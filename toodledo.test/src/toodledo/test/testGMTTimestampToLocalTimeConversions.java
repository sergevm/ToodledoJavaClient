package toodledo.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.domaindriven.toodledo.ToodledoTimestamp;

@RunWith(Parameterized.class)
public class testGMTTimestampToLocalTimeConversions {
	
	long timestamp;
	String expected;
	
	@Parameters
	public static Collection<Object[]> data() {
		
		Object[][] data = new Object[][]{
				{1343327400, "26/07/2012 18:30"},
				{1339582500,"13/06/2012 10:15"},
				{1327159800,"21/01/2012 15:30"},
				{1327172400,"21/01/2012 19:00"}};
		
		return Arrays.asList(data);
	}
	
	public testGMTTimestampToLocalTimeConversions(long timestamp, String expected) {
		this.timestamp = timestamp;
		this.expected = expected;
	}

	private SimpleDateFormat local_formatter;
	
	@Before
	public void setup() {
		local_formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");	
	}	
	
	@Test
	public void testTimestampTranslatesToDate() throws ParseException {
		
		Date localDate = ToodledoTimestamp.GetLocalDateTime(timestamp);
		assertEquals(expected, local_formatter.format(localDate));

	}
}
