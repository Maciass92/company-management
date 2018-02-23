package com.example.companymanagementsystem.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeCommand {

    private Long id;
    private String name;
    private String address;
    private String contact;
    private Integer wage;
}
