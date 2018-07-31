package com.example.demo.integration;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.ToDoListApplication;

import io.restassured.module.mockmvc.RestAssuredMockMvc;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ToDoListApplication.class)
@WebAppConfiguration
public class UserControllerIntegrationTest {

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void configureMockMvcInstance() {
        // RestAssuredMockMvc.postProcessors(csrf().asHeader());
        RestAssuredMockMvc.webAppContextSetup(wac);
    }

    @After
    public void restRestAssured() {
        RestAssuredMockMvc.reset();
    }
	
	@Test 
	public void greeting_resource_returns_expected_greeting_in_body() {
		
	/*	
		RestAssuredMockMvc.given().
	            param("name", "Johan").
	    when().
	            get("/greeting").
	    then().
	            body("content", equalTo("Hello, Johan!"));
		
		*/
	}

}
