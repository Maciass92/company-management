package com.example.companymanagementsystem.services;

import com.example.companymanagementsystem.commands.WorkdayCommand;
import com.example.companymanagementsystem.converters.WorkdayCommandToWorkday;
import com.example.companymanagementsystem.converters.WorkdayToWorkdayCommand;
import com.example.companymanagementsystem.model.Workday;
import com.example.companymanagementsystem.repositories.WorkdayRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WorkdayServiceImplementation implements WorkdayService {

    private final WorkdayRepository workdayRepository;
    private final WorkdayToWorkdayCommand workdayToWorkdayCommand;
    private final WorkdayCommandToWorkday workdayCommandToWorkday;

    public WorkdayServiceImplementation(WorkdayRepository workdayRepository, WorkdayToWorkdayCommand workdayToWorkdayCommand, WorkdayCommandToWorkday workdayCommandToWorkday) {
        this.workdayRepository = workdayRepository;
        this.workdayToWorkdayCommand = workdayToWorkdayCommand;
        this.workdayCommandToWorkday = workdayCommandToWorkday;
    }

    @Override
    public Workday findByDate(LocalDate date) {

        Optional<Workday> workdayOptional;

        workdayOptional = workdayRepository.findByDate(date);

        if (!workdayOptional.isPresent()) {
            System.out.println("Workday with of date: " + date.toString() + " not found.");
            return null;
        }

        return workdayOptional.get();
    }

    @Override
    public WorkdayCommand saveWorkdayCommand(WorkdayCommand workdayCommand) {

        Workday detachedWorkday = workdayCommandToWorkday.convert(workdayCommand);

        Workday savedWorkday = workdayRepository.save(detachedWorkday);

        return workdayToWorkdayCommand.convert(savedWorkday);
    }
}
