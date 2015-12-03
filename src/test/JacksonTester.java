package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;


public class JacksonTester {
	
	public static void omdbTest() throws JsonParseException, MalformedURLException, IOException
	{
		String url = "http://www.omdbapi.com/?t=101+Dalmatians&y=&plot=short&r=json";
	
		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(new URL(url));
		
		// continue parsing the token till the end of input is reached
		while(!parser.isClosed())
		{
			//get the token
			JsonToken token = parser.nextToken();
			
			//if its the last token then we are done
			if (token == null)
				break;
			
			// we want to look for a field that says Title
			if (JsonToken.FIELD_NAME.equals(token) && 
					"Title".equals(parser.getCurrentName()))
			{
				token = parser.nextToken();
				System.out.println(parser.getText());
			}
			
			// Gets Rating
			if (JsonToken.FIELD_NAME.equals(token) &&
					"Plot".equals(parser.getCurrentName()))
			{
				token = parser.nextToken();
				System.out.println(parser.getText());
			}
		}
	
	}
	
	
	
	
	
	public static void main(String[] args) throws JsonParseException, MalformedURLException, IOException
	{
		omdbTest();
	}
}
		/*
		// Get a list of albums from free music archive. limit the result to 5
		String url = "http://freemusicarchive.org/api/get/albums.json?api_key=60BLHNQCAOUFPIBZ&limit=5";
		
		
		//test with OMDb database
		//String url = "http://www.omdbapi.com/?t=Top+GUn&y=&plot=short&r=json";
		// get an instance of the json parser from the json factory
		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(new URL(url));
		
		// continue parsing the token till the end of input is reached
		while(!parser.isClosed())
		{
			//get the token
			JsonToken token = parser.nextToken();
			//if its the last toekn then we are done
			if (token == null)
				break;
			// we want to look for a field that syas dataset
			if (JsonToken.FIELD_NAME.equals(token) && 
					"dataset".equals(parser.getCurrentName()))
			{
				//We are entering the datasets now. The first token should be
				//start of array
				token = parser.nextToken();
				if(!JsonToken.START_ARRAY.equals(token))
				{
					//bail out
					break;
				}
				// each element of the array is an album so the next token
				// should be {
				token = parser.nextToken();
				if (!JsonToken.START_OBJECT.equals(token))
				{
					break;
				}
				// we are now looking for a field that says "album.title". We
				// continue looing till we find all such filed.s THis is probably
				// not a best way to parse this json, but this will suffice for this
				// example.
				while(true)
				{
					token = parser.nextToken();
					if(token == null)
						break;
					if(JsonToken.FIELD_NAME.equals(token) && 
							"album_title".equals(parser.getCurrentName()))
					{
						token = parser.nextToken();
						System.out.println(parser.getText());
					}
				}
				
			}
		}
*/