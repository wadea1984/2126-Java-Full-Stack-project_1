package com.ticketing_reimbursement.net.entity;

import jakarta.persistence.*;

@Entity
public class tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private Long employee_id;
    private String status;
    private String description;
    @Lob
    private byte[] receipt;
    public tickets(){

    }
    public tickets(Long id,double amount, Long employee_id, String status,String description){
        this.id=id;
        this.employee_id=employee_id;
        this.amount=amount;
        this.status=status;
        this.description=description;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public Long getEmployee_id() {
        return employee_id;
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

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
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
}
