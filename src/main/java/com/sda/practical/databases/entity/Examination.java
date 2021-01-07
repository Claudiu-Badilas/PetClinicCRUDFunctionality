package com.sda.practical.databases.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "examinations")

public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXAM_ID")
    private Integer examId;

    @Column(name = "CHECK_IN")
    private Date checkIn;

    @Column(name = "CHECK_OUT")
    private Date checkOut;

    @Column(name = "TREATMENT")
    private String treatment;



    @Column(name = "EMPLOYEE_ID", insertable = false, updatable = false)
    private Integer employeeId;

    @Column(name = "PET_ID", insertable = false, updatable = false)
    private Integer petId;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "PET_ID")
    private Pet pet;


    public Examination() {
    }

    public Examination(Date checkIn, Date checkOut, String treatment) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.treatment = treatment;

    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return examId + " | " + checkIn + " | " + checkOut + " | " + treatment ;
    }
}
