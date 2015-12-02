package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;

public class APITest {

	public APITest() throws MalformedURLException, IOException
	{
		String url = "http://www.omdbapi.com/";
		String charset = "UTF-8";
		String title = "Top Gun";
		//String season = "5";
		//String episode = "6";
		
		
		String query = String.format("t=%s&r=json",
				URLEncoder.encode(title, charset));
		
		URLConnection connection = new URL(url + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();

		//read the inputstream
		String theString = IOUtils.toString(response, charset);
		System.out.println(theString);
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException
	{
		new APITest();
	}
}
