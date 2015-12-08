package test;

import java.io.*;
import java.net.*;

import org.apache.commons.io.IOUtils;

public class APITest {

	public static String getJSON(String movieName) throws MalformedURLException, IOException
	{
		String url = "http://www.omdbapi.com/";
		String charset = "UTF-8";
		String title = movieName;
		
		String query = String.format("t=%s&r=json",
				URLEncoder.encode(title, charset));
		
		URLConnection connection = new URL(url + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();

		String theString = IOUtils.toString(response, charset);
		return theString;
	}
}
