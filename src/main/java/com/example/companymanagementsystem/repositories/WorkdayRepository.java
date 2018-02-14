package com.example.companymanagementsystem.repositories;

import com.example.companymanagementsystem.model.Workday;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkdayRepository extends CrudRepository<Workday, Long> {

    Optional<Workday> findByDate(LocalDate date);

    Iterable<Workday> findAllByDateBetween(LocalDate start, LocalDate end);
}
