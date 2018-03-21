package com.example.companymanagementsystem.converters;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.model.Employee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeCommandToEmployeeTest {

    EmployeeCommandToEmployee employeeCommandToEmployee;
    public static final Long EMPLOYEE_ID = 2L;
    public static final Integer WAGE = 12;
    public static final String CONTACT = "345-123-234";
    public static final String ADDRESS = "Junkyard Street 12, London";
    public static final String NAME = "Mike Wazowski";

    @Before
    public void setUp() throws Exception {

        employeeCommandToEmployee = new EmployeeCommandToEmployee();
    }

    @Test
    public void testNullObject() throws Exception {

        assertNull(employeeCommandToEmployee.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {

        assertNotNull(employeeCommandToEmployee.convert(new EmployeeCommand()));
    }

    @Test
    public void convertShouldReturnProperEmployeeObject() throws Exception {

        //given
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setId(EMPLOYEE_ID);
        employeeCommand.setWage(WAGE);
        employeeCommand.setContact(CONTACT);
        employeeCommand.setAddress(ADDRESS);
        employeeCommand.setName(NAME);

        //when
        Employee employee = employeeCommandToEmployee.convert(employeeCommand);

        //then
        assertNotNull(employee);
        assertEquals(EMPLOYEE_ID, employee.getId());
        assertEquals(WAGE, employee.getWage());
        assertEquals(CONTACT, employee.getContact());
        assertEquals(ADDRESS, employee.getAddress());
        assertEquals(NAME, employee.getName());
    }

}