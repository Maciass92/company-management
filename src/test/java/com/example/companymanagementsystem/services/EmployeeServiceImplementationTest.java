package com.example.companymanagementsystem.services;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.commands.WorkdayCommand;
import com.example.companymanagementsystem.commands.WorkdayForm;
import com.example.companymanagementsystem.converters.EmployeeCommandToEmployee;
import com.example.companymanagementsystem.converters.EmployeeToEmployeeCommand;
import com.example.companymanagementsystem.converters.WorkdayCommandToWorkday;
import com.example.companymanagementsystem.model.Employee;
import com.example.companymanagementsystem.model.Workday;
import com.example.companymanagementsystem.repositories.EmployeeRepository;
import com.example.companymanagementsystem.repositories.WorkdayRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplementationTest {

    EmployeeServiceImplementation employeeServiceImplementation;

    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    WorkdayRepository workdayRepository;
    @Mock
    EmployeeCommandToEmployee employeeCommandToEmployee;
    @Mock
    EmployeeToEmployeeCommand employeeToEmployeeCommand;
    @Mock
    WorkdayCommandToWorkday workdayCommandToWorkday;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        employeeServiceImplementation = new EmployeeServiceImplementation(employeeRepository, workdayRepository, employeeCommandToEmployee, employeeToEmployeeCommand, workdayCommandToWorkday);
    }

    @Test
    public void getEmployeesShouldReturnSortedListOfEmployees() throws Exception {

        //given
        Employee employee1 = new Employee();
        employee1.setId(1L);
        Employee employee2 = new Employee();
        employee2.setId(2L);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        when(employeeServiceImplementation.getEmployees()).thenReturn(employeeList);

        //when
        List<Employee> employees = employeeServiceImplementation.getEmployees();

        //then
        assertEquals(2, employees.size());
        assertEquals(employeeList, employees);
        verify(employeeRepository, times(1)).findAll();
        verify(employeeRepository, never()).findAllById(any());
    }

    @Test
    public void findEmployeeByIdShouldReturnSpecificEmployee() throws Exception {

        //given
        Employee employee = new Employee();
        employee.setId(1L);
        Optional<Employee> employeeOptional = Optional.of(employee);

        when(employeeRepository.findById(anyLong())).thenReturn(employeeOptional);

        //when
        Employee testEmployee = employeeServiceImplementation.findEmployeeById(1L);

        //then
        assertNotNull(testEmployee);
        assertEquals(new Long(1L), testEmployee.getId());
        verify(employeeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void saveEmployeeCommandShouldSaveOnlyOnce() throws Exception {

        //given
        Employee employee = new Employee();
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        //when
        Employee savedEmployee = employeeRepository.save(employee);

        //then
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void findCommandById() throws Exception {

        //given
        Employee employee = new Employee();
        employee.setId(1L);
        when(employeeServiceImplementation.findEmployeeById(anyLong())).thenReturn(employee);

        //when
        EmployeeCommand employeeCommand = employeeServiceImplementation.findCommandById(1L);

        //then
        assertNotNull(employeeCommand);
        assertEquals(employee.getId(), employeeCommand.getId());
        verify(employeeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void deleteEmployee() throws Exception {

        //given

        //when
        employeeRepository.deleteById(anyLong());

        //then
        verify(employeeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void findEmployeeWithFilteredWorkdaysAndPayments() throws Exception {


    }

    @Test
    public void getListOfWorkdayCommandsWithIds() throws Exception {

    }

    @Test
    public void saveWorkday() throws Exception {

        //given
        WorkdayCommand workdayCommand1 = new WorkdayCommand();
        WorkdayCommand workdayCommand2 = new WorkdayCommand();
        WorkdayCommand workdayCommand3 = new WorkdayCommand();

        WorkdayForm workdayForm = new WorkdayForm();
        workdayForm.setWorkdayCommands(new ArrayList<>());
        workdayForm.getWorkdayCommands().add(workdayCommand1);
        workdayForm.getWorkdayCommands().add(workdayCommand2);
        workdayForm.getWorkdayCommands().add(workdayCommand3);

        //when
        employeeServiceImplementation.saveWorkday(workdayForm);

        //then
        verify(workdayRepository, times(3)).save(any());

    }

    @Test
    public void returnDefaultDate() throws Exception {

        //given

        //when
        String testedDate = employeeServiceImplementation.returnDefaultDate();

        //then
        assertEquals(YearMonth.now().toString(), testedDate);
    }

}