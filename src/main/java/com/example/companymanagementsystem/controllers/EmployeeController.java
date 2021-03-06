package com.example.companymanagementsystem.controllers;

import com.example.companymanagementsystem.commands.EmployeeCommand;
import com.example.companymanagementsystem.commands.WorkdayCommand;
import com.example.companymanagementsystem.commands.WorkdayForm;
import com.example.companymanagementsystem.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public String showEmployees(Model model) {

        model.addAttribute("workdayForm", employeeService.getListOfWorkdayCommandsWithIds());

        return "showEmployees";
    }

    @RequestMapping("/employee/{id}")
    public String showSpecificEmployee(@PathVariable String id, @RequestParam(name = "date", required = false) String date, Model model){

        if(date == null)
            date = employeeService.returnDefaultDate();
                    //YearMonth.now().toString();

        model.addAttribute("employee", employeeService.findEmployeeWithFilteredWorkdaysAndPayments(new Long(id), date));

        return "specificEmployee";
    }

    @GetMapping("/new_employee")
    public String newEmployee(Model model){

        model.addAttribute("employee", new EmployeeCommand());

        return "newEmployee";
    }

    @RequestMapping("/employee")
    public String saveEmployee(@ModelAttribute EmployeeCommand commandObject){

        EmployeeCommand savedCommand = employeeService.saveEmployeeCommand(commandObject);

        return "redirect:/employee/" + savedCommand.getId();
    }

    @GetMapping("employee/{id}/edit")
    public String editEmployee(@PathVariable String id, Model model){

        model.addAttribute("employee", employeeService.findCommandById(new Long(id)));

        return  "newEmployee";
    }

    @GetMapping("employee/{id}/delete")
    public String deleteEmployee(@PathVariable String id){

        employeeService.deleteEmployee(new Long(id));

        return "redirect:/employees";
    }

    @RequestMapping("/addworkday")
    public String addWorkday(@ModelAttribute WorkdayForm workdayForm){

        employeeService.saveWorkday(workdayForm);

        return "redirect:/employees";
    }
}
