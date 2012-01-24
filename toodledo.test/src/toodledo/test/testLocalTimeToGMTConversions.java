package toodledo.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.domaindriven.toodledo.ToodledoTimestamp;

@RunWith(Parameterized.class)
public class testLocalTimeToGMTConversions {
	
	long timestamp;
	String localtime;
	
	@Parameters
	public static Collection<Object[]> data() {
		
		Object[][] data = new Object[][]{
				{1343327400, "26/07/2012 18:30"},
				{1339582500,"13/06/2012 10:15"},
				{1327159800,"21/01/2012 15:30"},
				{1327172400,"21/01/2012 19:00"}};
		
		return Arrays.asList(data);
	}
	
	public testLocalTimeToGMTConversions(long timestamp, String expected) {
		this.timestamp = timestamp;
		this.localtime = expected;
	}

	private SimpleDateFormat gmt_formatter;
	
	@Before
	public void setup() {
		gmt_formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		gmt_formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
	}	
	
	@Test
	public void testLocalTimeTranslatesToTimestamp() throws ParseException {
				
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(gmt_formatter.parse(localtime));
		long calculated = ToodledoTimestamp.GetGMTTimeInSeconds(calendar.getTime());
										
		assertEquals(timestamp, calculated);
	}
}
