package tools;

import java.text.*;
import java.util.*;

public class DateConversion {

	public static String parseDate(String unFormated) throws ParseException
	{
		String newFormat = "MM/dd/YYY";
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		Date date = formatter.parse(unFormated);
		formatter.applyPattern(newFormat);
		return formatter.format(date);
	}
	
	public static void main(String[] args) throws ParseException
	{
		System.out.println(parseDate("10 Jun 2010"));
	}
}
