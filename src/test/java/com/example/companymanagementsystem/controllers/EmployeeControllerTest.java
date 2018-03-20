package com.example.companymanagementsystem.controllers;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.commands.WorkdayCommand;
import com.example.companymanagementsystem.commands.WorkdayForm;
import com.example.companymanagementsystem.model.Employee;
import com.example.companymanagementsystem.services.EmployeeService;
import com.example.companymanagementsystem.services.EmployeeServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;


import java.time.YearMonth;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {

    EmployeeController employeeController;
    MockMvc mockMvc;

    @Mock
    EmployeeService employeeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        employeeController = new EmployeeController(employeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void showEmployeesShouldDisplayAListOfEmployees() throws Exception {

        //given
        WorkdayForm workdayForm = new WorkdayForm();

        //when
        when(employeeService.getListOfWorkdayCommandsWithIds()).thenReturn(workdayForm);

        //then
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(view().name("showEmployees"))
                .andExpect(model().attributeExists("workdayForm"));
        verify(employeeService, times(1)).getListOfWorkdayCommandsWithIds();
    }

    @Test
    public void showSpecificEmployeeShouldDisplayEmployeeDetails() throws Exception {

        //given
        String date = "2018-10";
        Employee employee = new Employee();

        //when
        when(employeeService.returnDefaultDate()).thenReturn(date);
        when(employeeService.findEmployeeWithFilteredWorkdaysAndPayments(anyLong(), anyString())).thenReturn(employee);

        //then
        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("specificEmployee"))
                .andExpect(model().attributeExists("employee"));

        verify(employeeService, times(1)).findEmployeeWithFilteredWorkdaysAndPayments(anyLong(), anyString());
        verify(employeeService, times(1)).returnDefaultDate();
    }

    @Test
    public void newEmployeeShouldDisplayEmployeeCreationForm() throws Exception {

        mockMvc.perform(get("/new_employee"))
                .andExpect(status().isOk())
                .andExpect(view().name("newEmployee"))
                .andExpect(model().attributeExists("employee"));
    }

    @Test
    public void saveEmployeeShouldRedirectToAViewWithSpecificId() throws Exception {

        //given
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setId(2L);

        //when
        when(employeeService.saveEmployeeCommand(any())).thenReturn(employeeCommand);

        //then
        mockMvc.perform(post("/employee"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employee/2"));


        verify(employeeService, times(1)).saveEmployeeCommand(any());
    }

    @Test
    public void editEmployeeShouldRedirectToAFormWithEmployeeInformation() throws Exception {

        //given
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setId(2L);

        //when
        when(employeeService.findCommandById(employeeCommand.getId())).thenReturn(employeeCommand);

        //then
        mockMvc.perform(get("/employee/2/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("newEmployee"))
                .andExpect(model().attributeExists("employee"));

        verify(employeeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void deleteEmployeeShouldRedirectToShowEmployees() throws Exception {

        mockMvc.perform(get("/employee/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees"));

        verify(employeeService, times(1)).deleteEmployee(anyLong());
    }

    @Test
    public void addWorkdayShouldRedirectToShowEmployees() throws Exception {

        mockMvc.perform(post("/addworkday"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees"));

        verify(employeeService, times(1)).saveWorkday(any());
    }

}