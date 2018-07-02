package com.example.easynotes.controller;

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

import com.experian.buid.easynotes.AppConstants;

//http://www.baeldung.com/spring-boot-testing
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class NoteControllerTest {

	private static final String API_PATH = "https://localhost:8080" + AppConstants.Api.CONTEXT_PATH
			+ AppConstants.Notes.PATH;

	@Autowired
	private MockMvc mvc;

	@Before
	public void setup() {
	}

	@Test
	private void performGenerateTokenReq() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get(API_PATH)
					.accept(MediaType.TEXT_HTML))
					.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
