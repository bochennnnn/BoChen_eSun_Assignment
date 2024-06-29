package com.BCesunProject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BCesunProject.dto.SeatingChartDTO;
import com.BCesunProject.model.bean.Employee;
import com.BCesunProject.model.bean.SeatingChart;
import com.BCesunProject.service.EmployeeService;
import com.BCesunProject.service.SeatService;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public List<SeatingChartDTO> getallseat() {
        List<SeatingChart> allSeatList = seatService.allSeat();
        List<SeatingChartDTO> seatingChartDTOList = new ArrayList<>();

        for (SeatingChart seatingChart : allSeatList) {
            SeatingChartDTO dto = new SeatingChartDTO();
            BeanUtils.copyProperties(seatingChart, dto);
            if (seatingChart.getEmployee() != null) {
                dto.setEmpId(seatingChart.getEmployee().getEmpId());
            }
            seatingChartDTOList.add(dto);
        }

        return seatingChartDTOList;
    }

    @GetMapping("/api/checkseat")
    public Boolean checkseat(@RequestParam Integer floorSeatSeq) {
        Boolean isEmpty = seatService.checkSeat(floorSeatSeq);
        return isEmpty;
    }

    @PutMapping("/api/updateseat")
    public ResponseEntity<String> updateSeat(@RequestParam String empId, @RequestParam Integer floorSeatSeq) {
        employeeService.updateEmployee(empId, floorSeatSeq);
        return ResponseEntity.ok().body("seat order success");
    }

    @PutMapping("/api/removeseat")
    public ResponseEntity<Integer> removeSeat(@RequestParam String empId) {
        Integer exfloorSeatSeq = employeeService.removeSeat(empId);
        return ResponseEntity.ok().body(exfloorSeatSeq);
    }
}
