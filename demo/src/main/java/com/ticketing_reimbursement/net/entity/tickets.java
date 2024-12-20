package com.ticketing_reimbursement.net.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private Long employeeID;
    private String status;
    private String description;

    private String receipt;
    private String name;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public tickets(){

    }
    public tickets(Long id,double amount, Long employee_id, String status,
                   String description,String name,LocalDate date){
        this.id=id;
        this.employeeID=employee_id;
        this.amount=amount;
        this.status=status;
        this.description=description;
        this.name=name;
        this.date=date;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployeeID(Long employee_id) {
        this.employeeID = employee_id;
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "tickets{" +
                "id=" + id +
                ", amount=" + amount +
                ", employee_id=" + employeeID +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
