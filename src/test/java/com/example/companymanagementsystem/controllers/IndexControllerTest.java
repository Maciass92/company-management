package com.example.companymanagementsystem.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController indexController;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        indexController = new IndexController();
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void getMainPageTest() throws Exception {

        String returnedByMethod = indexController.getMainPage();
        assertEquals("main", returnedByMethod);

        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(view().name("main"));
    }

}