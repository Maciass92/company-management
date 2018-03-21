package com.example.companymanagementsystem.converters;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.model.Employee;
import com.example.companymanagementsystem.model.Workday;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class EmployeeToEmployeeCommandTest {

    EmployeeToEmployeeCommand employeeToEmployeeCommand;
    public static final Long EMPLOYEE_ID = 2L;
    public static final Integer WAGE = 12;
    public static final String CONTACT = "345-123-234";
    public static final String ADDRESS = "Junkyard Street 12, London";
    public static final String NAME = "Mike Wazowski";

    @Before
    public void setUp() throws Exception {

        employeeToEmployeeCommand = new EmployeeToEmployeeCommand();
    }

    @Test
    public void testNullObject() throws Exception {

        assertNull(employeeToEmployeeCommand.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(employeeToEmployeeCommand.convert(new Employee()));
    }

    @Test
    public void convertShouldReturnProperEmployeeCommandObject() throws Exception {

        //given
        Employee employee = new Employee();
        employee.setId(EMPLOYEE_ID);
        employee.setContact(CONTACT);
        employee.setAddress(ADDRESS);
        employee.setWage(WAGE);
        employee.setName(NAME);

        //when
        EmployeeCommand employeeCommand = employeeToEmployeeCommand.convert(employee);

        //then
        assertNotNull(employeeCommand);
        assertEquals(EMPLOYEE_ID, employeeCommand.getId());
        assertEquals(WAGE, employeeCommand.getWage());
        assertEquals(CONTACT, employeeCommand.getContact());
        assertEquals(ADDRESS, employeeCommand.getAddress());
        assertEquals(NAME, employeeCommand.getName());


    }

}