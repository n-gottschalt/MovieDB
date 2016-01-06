package databaseJSON;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JFrame;

import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;

public class JacksonAPITest {

	@Test
	public void testMovieTitle() throws JsonParseException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		assertEquals("Top Gun", JacksonAPI.pullFromOMDB("Top Gun", 0, new JFrame()).get("Title"));
	}
	
	@Test
	public void testMovieRunTime() throws JsonParseException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		assertEquals("110 min", JacksonAPI.pullFromOMDB("Top Gun", 0, new JFrame()).get("Runtime"));
	}
	@Test
	public void testMovieRunTimeAgain() throws JsonParseException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		assertEquals("Iron Man 3", JacksonAPI.pullFromOMDB("Iron Man 3", 0, new JFrame()).get("Title"));
	}
	@Test
	public void testArt() throws JsonParseException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		assertNotNull(JacksonAPI.pullFromOMDB("UP", 0, new JFrame()).get("Art"));
	}
	
	@Test
	public void testReview() throws JsonParseException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException
	{
		assertEquals("6.9", JacksonAPI.pullFromOMDB("Top Gun", 0, new JFrame()).get("imdbRating"));
	}
	
	@Test
	public void testIMDBid() throws JsonParseException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException
	{
		assertEquals("The Lion King", JacksonAPI.pullFromOMDB("tt0110357", 1, new JFrame()).get("Title"));
	}

}
