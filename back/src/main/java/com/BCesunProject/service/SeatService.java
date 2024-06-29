package com.BCesunProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BCesunProject.model.SeatingChartRepository;
import com.BCesunProject.model.bean.Employee;
import com.BCesunProject.model.bean.SeatingChart;

@Service
public class SeatService {
    @Autowired
    private SeatingChartRepository seatingChartRepo;

    public SeatingChart findSeat(Integer floorSeatSeq) {
        return seatingChartRepo.findByfloorSeatSeq(floorSeatSeq);
    }

    public List<SeatingChart> allSeat() {
        return seatingChartRepo.findAll();
    }

    public Boolean checkSeat(Integer floorSeatSeq) {
        SeatingChart seat = seatingChartRepo.findByfloorSeatSeq(floorSeatSeq);
        Employee emp = seat.getEmployee();
        return emp == null;
    }

}
