package com.form3.hometest;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = HometestApplication.class)
@WebAppConfiguration
@SpringBootTest
public class HometestApplicationTests {

	@Test
	public void contextLoads() {
	}

}
