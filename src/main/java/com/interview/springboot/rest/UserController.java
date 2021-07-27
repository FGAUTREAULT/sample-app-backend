package com.interview.springboot.rest;

import java.util.concurrent.atomic.AtomicInteger;

import com.interview.springboot.rest.model.Greeting;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// Don't bother with cors here
@RestController
@CrossOrigin
@RequestMapping("/users")
@Api(value = "users", description = "Operations to manage users and greet on connection.")
public class UserController {

    private static final String GREETING_TEMPLATE = "Hello, %s!";
    private final AtomicInteger counter = new AtomicInteger();

    @ApiOperation(value = "Get a greeting message for either default or customized with username", response = Greeting.class)
    @RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = "application/json")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(GREETING_TEMPLATE, name));
    }

}
