package com.experian.buname.easynotes.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.experian.buname.easynotes.AppConstants;
import com.experian.buname.easynotes.EasyNotesApplication;
import com.jayway.jsonpath.JsonPath;

//http://www.baeldung.com/spring-boot-testing
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyNotesApplication.class)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class NoteControllerTest {

	private static final String API_PATH = AppConstants.Notes.PATH_V1;

	@Autowired
	private MockMvc mvc;

	@Before
	public void setup() {
	}

	@Test
	public void createAndGetNoteTest() throws Exception {
		String resp = mvc.perform(
				MockMvcRequestBuilders.post(API_PATH)
					.content("{ \"content\": \"Content 1\", \"title\": \"Note 1\"}")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
					.andReturn().getResponse().getContentAsString();
		
		Integer id = JsonPath.read(resp, "$.id");
		System.out.println(id);
		mvc.perform(
				MockMvcRequestBuilders.get(API_PATH + "/{id}", id)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(id)));
	}

}
