package com.example.companymanagementsystem.converters;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.model.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCommandToEmployee implements Converter<EmployeeCommand, Employee> {


    @Nullable
    @Override
    public Employee convert(EmployeeCommand source) {

        if(source == null)
            return null;

        final Employee employee = new Employee();
            employee.setId(source.getId());
            employee.setName(source.getName());
            employee.setWage(source.getWage());
            employee.setAddress(source.getAddress());
            employee.setContact(source.getContact());

            return employee;
    }
}
