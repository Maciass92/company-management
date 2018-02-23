package com.example.companymanagementsystem.converters;

import com.example.companymanagementsystem.commands.WorkdayCommand;
import com.example.companymanagementsystem.model.Workday;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class WorkdayToWorkdayCommand implements Converter<Workday, WorkdayCommand> {

    @Synchronized
    @Nullable
    @Override
    public WorkdayCommand convert(Workday source) {
        if(source == null)
            return null;

        final WorkdayCommand commandObject = new WorkdayCommand();

        if (source.getEmployee() != null) {
            commandObject.setEmployeeId(source.getEmployee().getId());
        }

        commandObject.setAdvancePayment(source.getAdvancePayment());
        commandObject.setDate(source.getDate());
        commandObject.setHoursWorked(source.getHoursWorked());
        commandObject.setId(source.getId());

        return commandObject;
    }
}
