package com.BCesunProject.model.bean;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "SeatingChart")
public class SeatingChart {
    @Id
    @Column(name = "FLOOR_SEAT_SEQ")
    private Integer floorSeatSeq;

    @Column(name = "FLOOR_NO")
    private Integer floorNo;

    @Column(name = "SEAT_NO")
    private Integer seatNo;

    @OneToOne(mappedBy = "seatingChart")
    @JsonManagedReference
    private Employee employee;
}
