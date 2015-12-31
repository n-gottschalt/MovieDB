package tools;

import java.text.*;
import java.util.*;

public class DateConversion {

	public static String parseDate(String unFormated) throws ParseException
	{
		if(!(unFormated.contains("/")))
		{
			String newFormat = "MM/dd/YYY";
			SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
			Date date = formatter.parse(unFormated);
			formatter.applyPattern(newFormat);
			return formatter.format(date);
		}
		else
			//Work on error issues
			return unFormated;
	}
}
