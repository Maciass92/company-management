package com.example.companymanagementsystem.repositories;

import com.example.companymanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    //both unused, should be deleted, but I want to keep them
    @Query("SELECT count(*) FROM Employee")
    Long getNumberOfEmployees();

    @Query("SELECT id FROM Employee")
    List<Long> getListOfIds();
}
