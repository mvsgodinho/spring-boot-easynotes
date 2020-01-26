package com.example.easynotes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyNotesApplication.class)
@TestPropertySource({"classpath:application.properties", "classpath:application-createdb.properties"})
public class DBScriptCreationTest {

	@Test
	public void justCreateDBScript() {}
}
