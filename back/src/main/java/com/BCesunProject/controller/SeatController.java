package com.BCesunProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BCesunProject.model.bean.Employee;
import com.BCesunProject.model.bean.SeatingChart;
import com.BCesunProject.service.EmployeeService;
import com.BCesunProject.service.SeatService;

@Controller
@RestController
public class SeatController {
    @Autowired
    private SeatService seatService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/api/allemployee")
    public List<Employee> getemployee() {
        List<Employee> allEmployee = employeeService.findAllEmployee();
        return allEmployee;
    }

    @GetMapping("/api/allseat")
    public List<SeatingChart> getallseat() {
        List<SeatingChart> allSeatList = seatService.allSeat();
        return allSeatList;
    }

    // @GetMapping("/index")
    // @ResponseBody
    // public Integer getMethodName() {
    // SeatingChart seat = seatService.findSeat(1);
    // Integer seatno = seat.getFloorSeatSeq();
    // return seatno;
    // }

}
