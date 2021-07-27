package com.interview.springboot.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.interview.springboot.rest.model.Greeting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTests {

    @Autowired
    private UserController controller;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(controller);
    }

    @Test
    public void greetings() throws Exception {
        String name = "John";
        Greeting greeting = new Greeting(0, String.format("Hello, %s!", name));
        assertEquals(greeting.getContent(), controller.greeting(name).getContent());
    }
}
