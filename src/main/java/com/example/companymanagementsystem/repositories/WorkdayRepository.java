package com.example.companymanagementsystem.repositories;

import com.example.companymanagementsystem.model.Workday;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkdayRepository extends CrudRepository<Workday, Long> {

    Optional<Workday> findByDate(LocalDate date);

    /*@Query("select b from Book b " +
            "where b.from between ?1 and ?2 and b.to between ?1 and ?2")*/
    Iterable<Workday> findAllByEmployeeIdAndDateBetween( Long id, LocalDate start, LocalDate end);
}
