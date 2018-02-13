package com.example.companymanagementsystem.commands;


import com.example.companymanagementsystem.model.Workday;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeCommand {

    private Long id;
    private String name;
    private String address;
    private String contact;
    private Integer wage;
    private List<Workday> workdays = new ArrayList<>();
    }
