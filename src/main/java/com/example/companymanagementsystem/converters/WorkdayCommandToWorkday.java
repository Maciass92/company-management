package com.example.companymanagementsystem.converters;

import com.example.companymanagementsystem.commands.WorkdayCommand;
import com.example.companymanagementsystem.model.Employee;
import com.example.companymanagementsystem.model.Workday;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WorkdayCommandToWorkday implements Converter<WorkdayCommand, Workday> {

    @Synchronized
    @Nullable
    @Override
    public Workday convert(WorkdayCommand source) {

        if(source == null)
            return null;

        final Workday workday = new Workday();
        workday.setHoursWorked(source.getHoursWorked());
        workday.setAdvancePayment(source.getAdvancePayment());
        workday.setId(source.getId());
        workday.setDate(LocalDate.now());

        if(source.getEmployeeId() != null) {
            Employee employee = new Employee();
            employee.setId(source.getEmployeeId());
            workday.setEmployee(employee);
            employee.addWorkday(workday);
        }

        return workday;
    }
}
