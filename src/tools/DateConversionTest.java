package tools;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class DateConversionTest {

	@Test
	public void test() throws ParseException {
		assertEquals("10/31/1994", DateConversion.parseDate("31 Oct 1994"));
	}

}
