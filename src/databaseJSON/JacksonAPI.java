package databaseJSON;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.core.*;
import org.apache.http.util.*;

public class JacksonAPI {
	
	private static String[] types = {"Title", "Released", "imdbRating", "Director", "Genre", "Runtime", "Plot", "Poster"};
	
	public static LinkedHashMap<String, Object> pullFromOMDB(String movieName, int type) throws 
	JsonParseException, MalformedURLException, IOException, ClassNotFoundException, InstantiationException, 
	IllegalAccessException
	{
		LinkedHashMap<String, Object> dataFromJSON = new LinkedHashMap<String, Object>();
		String nameQuery;
		
		if(type == 0)
			nameQuery = String.format("t=%s&r=json", URLEncoder.encode(movieName, "UTF-8"));
		else
			nameQuery = String.format("i=%s&r=json", URLEncoder.encode(movieName, "UTF-8"));
		String url = "http://www.omdbapi.com/?" + nameQuery;
	
		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(new URL(url));
		
		while(!parser.isClosed())
		{
			JsonToken token = parser.nextToken();
			
			if (token == null)
				break;

			for(int i = 0; i < 8; i++)
			{
				if (JsonToken.FIELD_NAME.equals(token) &&
						types[i].equals(parser.getCurrentName()))
				{
					token = parser.nextToken();
					if(types[i].equals("Poster"))
						dataFromJSON.put("Art", addPictureToHash(new URL(parser.getText())));
					else
						dataFromJSON.put(types[i], parser.getText());
				}
			}
		}
		return dataFromJSON;
	}
	
	private static byte[] addPictureToHash(URL picture) throws IOException
	{
		URLConnection ucon = picture.openConnection();
		
		InputStream is = ucon.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		
		ByteArrayBuffer baf = new ByteArrayBuffer(500);
		int current = 0;
		while((current = bis.read()) != -1)
			baf.append((byte) current);
		return baf.toByteArray();
	}
}