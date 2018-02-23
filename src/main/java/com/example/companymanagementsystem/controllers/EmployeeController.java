package com.example.companymanagementsystem.controllers;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.commands.WorkdayCommand;
import com.example.companymanagementsystem.services.EmployeeService;
import com.example.companymanagementsystem.services.WorkdayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RequiredArgsConstructor
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final WorkdayService workdayService;

    @RequestMapping("/employees")
    public String showEmployees(Model model) {

        model.addAttribute("employees", employeeService.getEmployees());
        model.addAttribute("workday", new WorkdayCommand());

        return "employees";
    }

    @RequestMapping("/employee/{id}")
    public String showSpecificEmployee(@PathVariable String id, @RequestParam(name = "date", required = false) String date, Model model){

        if(date == null) {

            date = YearMonth.now().toString();
            model.addAttribute("employee", employeeService.findEmployeeWithFilteredWorkdaysAndPayments(new Long(id), date));
        }

        model.addAttribute("employee", employeeService.findEmployeeWithFilteredWorkdaysAndPayments(new Long(id), date));

        return "specificEmployee";
    }

    @GetMapping("/new_employee")
    public String newEmployee(Model model){

        model.addAttribute("employee", new EmployeeCommand());

        return "newEmployee";
    }

    @PostMapping("/employee")
    public String saveOrEdit(@ModelAttribute EmployeeCommand commandObject){

        EmployeeCommand savedCommand = employeeService.saveEmployeeCommand(commandObject);

        return "redirect:/employee/" + savedCommand.getId();
    }

    @GetMapping("employee/{id}/edit")
    public String editEmployee(@PathVariable String id, Model model){

        model.addAttribute("employee", employeeService.findCommandById(new Long(id)));

        return  "newEmployee";
    }

    @GetMapping("employee/{id}/delete")
    public String deleteEmployee(@PathVariable String id, Model model){

        employeeService.deleteEmployee(new Long(id));
        model.addAttribute("employees", employeeService.getEmployees());

        return "employees";
    }


    @PostMapping
    @RequestMapping("/addworkday")
    public String addWorkday(@ModelAttribute WorkdayCommand workdayCommand){

        workdayService.saveWorkdayCommand(workdayCommand);

        return "redirect:/employees";
    }
}
