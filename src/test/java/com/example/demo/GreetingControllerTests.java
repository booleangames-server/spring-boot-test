package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingControllerTests {
 
    @Test
    public void home() throws Exception {
        standaloneSetup(new GreetingController()).build()
        .perform(get("/greeting"))
        .andExpect(status().isOk());
    }
}