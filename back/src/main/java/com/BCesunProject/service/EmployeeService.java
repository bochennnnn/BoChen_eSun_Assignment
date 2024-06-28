package com.BCesunProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BCesunProject.model.EmployeeRepository;
import com.BCesunProject.model.bean.Employee;
import com.BCesunProject.model.bean.SeatingChart;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private SeatService seatService;

    public List<Employee> findAllEmployee() {
        return employeeRepo.findAll();
    }

    public Employee updatEmployee(String empId, Integer floorSeatSeq) {
        Employee emp = employeeRepo.findByEmpId(empId);
        SeatingChart seat = seatService.findSeat(floorSeatSeq);
        // System.out.println(emp.getEmpId());
        emp.setSeatingChart(seat);
        return employeeRepo.save(emp);
    }
}
