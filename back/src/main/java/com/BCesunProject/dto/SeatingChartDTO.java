package com.BCesunProject.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatingChartDTO {
    private Integer floorSeatSeq;
    private Integer floorNo;
    private Integer seatNo;
    private String empId;

}
