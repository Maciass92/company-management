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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WorkdayRepository workdayRepository;
    private final EmployeeCommandToEmployee employeeCommandToEmployee;
    private final EmployeeToEmployeeCommand employeeToEmployeeCommand;
    private final WorkdayCommandToWorkday workdayCommandToWorkday;

    @Override
    public List<Employee> getEmployees() {

        List<Employee> employeeList = new ArrayList<>();

        employeeRepository.findAll().iterator().forEachRemaining(employeeList::add);

        Collections.sort(employeeList);

        return employeeList;
    }

    @Override
    public Employee findEmployeeById(Long id) {

        Optional<Employee> employee;

        employee = employeeRepository.findById(id);

        if (!employee.isPresent()) {
            System.out.println("Employee with an id: " + id.toString() + " not found.");
            return null;
        }

        return employee.get();
    }

    @Override
    @Transactional
    public EmployeeCommand saveEmployeeCommand(EmployeeCommand employeeCommand) {

        Employee detachedEmployee = employeeCommandToEmployee.convert(employeeCommand);

        Employee savedEmployee = employeeRepository.save(detachedEmployee);

        return employeeToEmployeeCommand.convert(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeeCommand findCommandById(Long id) {

        EmployeeCommand employeeCommand = employeeToEmployeeCommand.convert(findEmployeeById(id));

        return employeeCommand;
    }

    @Override
    public void deleteEmployee(Long id) {

        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findEmployeeWithFilteredWorkdaysAndPayments(Long id, String date) {

        Employee employee = findEmployeeById(id);

        List<Workday> filteredWorkdays = new ArrayList<>();

        //todo Separate method for returning startDate & endDate
        StringBuilder startDateString;

        if (date.isEmpty())
            startDateString = new StringBuilder(YearMonth.now().toString());
        else
            startDateString = new StringBuilder(date);

        startDateString.append("-01");

        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = startDate.plusMonths(1);

        workdayRepository.findAllByEmployeeIdAndDateBetween(id, startDate, endDate).iterator().forEachRemaining(filteredWorkdays::add);

        Collections.sort(filteredWorkdays);

        employee.setWorkdays(filteredWorkdays);

        return employee;
    }

    @Override
    public WorkdayForm getListOfWorkdayCommandsWithIds(){

        List<WorkdayCommand> listOfCommands = new ArrayList<>();
        List<Employee> listOfEmployees = this.getEmployees();

        for(int i = 0; i < listOfEmployees.size(); i++){

            WorkdayCommand workdayCommand = new WorkdayCommand();
            workdayCommand.setEmployee(listOfEmployees.get(i));
            workdayCommand.setEmployeeId(listOfEmployees.get(i).getId());
            listOfCommands.add(workdayCommand);
        }

        WorkdayForm workdayForm = new WorkdayForm();
        workdayForm.setWorkdayCommands(listOfCommands);

        return workdayForm;
    }

    @Override
    public void saveWorkday(WorkdayForm workdayForm) {

        for(int i = 0; i < workdayForm.getWorkdayCommands().size(); i++){

            Workday detachedWorkday = workdayCommandToWorkday.convert(workdayForm.getWorkdayCommands().get(i));

            System.out.println(detachedWorkday.getDate());
            System.out.println(detachedWorkday.getAdvancePayment());
            System.out.println(detachedWorkday.getHoursWorked());


            workdayRepository.save(detachedWorkday);
        }

    }
}
