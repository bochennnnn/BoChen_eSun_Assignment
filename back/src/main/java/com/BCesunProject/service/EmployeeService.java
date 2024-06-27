package com.BCesunProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BCesunProject.model.EmployeeRepository;
import com.BCesunProject.model.bean.Employee;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    public List<Employee> findAllEmployee() {
        return employeeRepo.findAll();
    }
}
