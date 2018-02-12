package com.example.companymanagementsystem.converters;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.model.Employee;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EmployeeToEmployeeCommand implements Converter<Employee, EmployeeCommand> {

    @Synchronized
    @Nullable
    @Override
    public EmployeeCommand convert(Employee source) {
        if(source == null)
            return null;

        final EmployeeCommand commandObject = new EmployeeCommand();
        commandObject.setId(source.getId());
        commandObject.setName(source.getName());
        commandObject.setAddress(source.getAddress());
        commandObject.setContact(source.getContact());
        commandObject.setWage(source.getWage());

        return commandObject;
    }
}
