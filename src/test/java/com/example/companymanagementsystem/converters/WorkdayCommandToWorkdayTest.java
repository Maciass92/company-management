package com.example.companymanagementsystem.converters;

import com.example.companymanagementsystem.commands.WorkdayCommand;
import com.example.companymanagementsystem.model.Employee;
import com.example.companymanagementsystem.model.Workday;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class WorkdayCommandToWorkdayTest {

    WorkdayCommandToWorkday workdayCommandToWorkday;
    public static final Long EMPLOYEE_ID = 2L;
    public static final Long WORKDAY_ID = 1L;
    public static final Integer HOURS_WORKED = 8;
    public static final Integer ADV_PAYMENTS = 1200;

    @Before
    public void setUp() throws Exception {
        workdayCommandToWorkday = new WorkdayCommandToWorkday();
    }

    @Test
    public void testNullObject() throws Exception {

        assertNull(workdayCommandToWorkday.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {

        assertNotNull(workdayCommandToWorkday.convert(new WorkdayCommand()));
    }

    @Test
    public void convertShouldReturnProperWorkdayObject() throws Exception {

        //given
        WorkdayCommand workdayCommand = new WorkdayCommand();
        workdayCommand.setId(WORKDAY_ID);
        workdayCommand.setHoursWorked(HOURS_WORKED);
        workdayCommand.setAdvancePayment(ADV_PAYMENTS);
        workdayCommand.setEmployeeId(EMPLOYEE_ID);

        Employee employee = new Employee();
        workdayCommand.setEmployee(employee);
        List<Workday> workdayArrayList = employee.getWorkdays();

        //when
        Workday workday = workdayCommandToWorkday.convert(workdayCommand);

        //then
        assertNotNull(workday);
        assertEquals(WORKDAY_ID, workday.getId());
        assertEquals(HOURS_WORKED, workday.getHoursWorked());
        assertEquals(ADV_PAYMENTS, workday.getAdvancePayment());
        assertEquals(EMPLOYEE_ID, workday.getEmployee().getId());
        assertEquals(LocalDate.now(), workday.getDate());
        assertEquals(EMPLOYEE_ID, workday.getEmployee().getId());
        assertEquals(workdayArrayList.size()+1, workday.getEmployee().getWorkdays().size());
        assertEquals(1, workday.getEmployee().getWorkdays().size());
    }

}