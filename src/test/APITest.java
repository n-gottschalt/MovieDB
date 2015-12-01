package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class APITest {

	public APITest() throws MalformedURLException, IOException
	{
		String url = "http://www.omdbapi.com/";
		String charset = "UTF-8";
		String title = "Game of Thrones";
		String season = "5";
		String episode = "5";
		
		String query = String.format("t=%s&season=%s&episode=%s",
				URLEncoder.encode(title, charset),
				URLEncoder.encode(season, charset),
				URLEncoder.encode(episode, charset));
		
		URLConnection connection = new URL(url + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();

		//read the inputstream
		String theString = IOUtils.toString(response, "UTF-8");
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException
	{
		new APITest();
	}
}
