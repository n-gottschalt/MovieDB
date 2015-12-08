package databaseJSON;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;

public class JacksonAPITest {

	@Test
	public void test() throws JsonParseException, MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		assertEquals("Top Gun", JacksonAPI.pullFromOMDB("Top Gun").get("Title"));
		assertEquals("110 min", JacksonAPI.pullFromOMDB("Top Gun").get("Runtime"));
		assertEquals("Iron Man 3", JacksonAPI.pullFromOMDB("Iron Man 3").get("Title"));
	}

}
