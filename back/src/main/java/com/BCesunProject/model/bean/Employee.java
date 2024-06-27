package com.BCesunProject.model.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "EMP_ID", length = 5)
    private String empId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "FLOOR_SEAT_SEQ", referencedColumnName = "FLOOR_SEAT_SEQ")
    @JsonBackReference
    private SeatingChart seatingChart;

}
