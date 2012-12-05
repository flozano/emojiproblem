package com.kii.emojiproblem;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class EmojiProblemTest {

	private static final String EMOJI = "\uD83D\uDC43";

	@Test
	public void testEmojiDeserialization() throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		String s = "{ \"name\": \"John\", \"lastName\": \"" + EMOJI + "\" }s ";
		Person p = om.readValue(s, Person.class);
		assertEquals("John", p.getName());
		assertEquals(EMOJI, p.getLastName());
	}

	@Test
	public void testEmojiSerialization() throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		Person p = new Person();
		p.setName("John");
		p.setLastName(EMOJI);
		String s = om.writeValueAsString(p);
		Person p2 = om.readValue(s, Person.class);
		assertEquals(EMOJI, p2.getLastName());
		assertEquals(p.getName(), p2.getName());
	}

	public static class Person {
		private String name;
		private String lastName;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

	}
}
