package com.example.companymanagementsystem.repositories;

import com.example.companymanagementsystem.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Optional<Employee> findByName(String name);
}
