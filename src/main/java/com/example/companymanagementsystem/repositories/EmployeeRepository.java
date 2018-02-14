package com.example.companymanagementsystem.repositories;

import com.example.companymanagementsystem.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
