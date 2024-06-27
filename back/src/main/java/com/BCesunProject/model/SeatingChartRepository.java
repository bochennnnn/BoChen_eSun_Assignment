package com.BCesunProject.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BCesunProject.model.bean.SeatingChart;

public interface SeatingChartRepository extends JpaRepository<SeatingChart, Integer> {
    SeatingChart findByfloorSeatSeq(Integer floorSeatSeq);

    List<SeatingChart> findAll();

}
