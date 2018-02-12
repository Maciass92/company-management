package com.example.companymanagementsystem.converters;

import com.example.companymanagementsystem.commands.WorkdayCommand;
import com.example.companymanagementsystem.model.Workday;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class WorkdayCommandToWorkday implements Converter<WorkdayCommand, Workday> {

    @Synchronized
    @Nullable
    @Override
    public Workday convert(WorkdayCommand source) {
        if(source == null)
            return null;

        final Workday workday = new Workday();
        workday.setDate(source.getDate());
        workday.setId(source.getId());
        workday.setAdvancePayment(source.getAdvancePayment());
        workday.setHoursWorked(source.getHoursWorked());

        return workday;
    }
}
