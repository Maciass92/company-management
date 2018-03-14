package com.example.companymanagementsystem.repositories;

import com.example.companymanagementsystem.model.Workday;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface WorkdayRepository extends CrudRepository<Workday, Long> {

    Iterable<Workday> findAllByEmployeeIdAndDateBetween( Long id, LocalDate start, LocalDate end);
}
