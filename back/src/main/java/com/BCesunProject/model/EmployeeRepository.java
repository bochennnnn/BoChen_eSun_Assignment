package com.BCesunProject.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.BCesunProject.model.bean.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findAll();
}
