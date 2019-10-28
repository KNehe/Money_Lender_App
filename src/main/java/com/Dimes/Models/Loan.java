package com.Dimes.Models;


import javax.persistence.*;

@Entity
@Table(name = "Loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String borrowerName; //borrower's name
    private String phoneNumber;
    private String nationalIdNumber;
    private double loanAmount;
    private String periodType;
    private int loanPeriod;
    private double deposit;


    public Loan() {}

    public Loan(String borrowerName, String phoneNumber, String nationalIdNumber, double loanAmount, String periodType, int loanPeriod, double deposit) {
        this.borrowerName = borrowerName;
        this.phoneNumber = phoneNumber;
        this.nationalIdNumber = nationalIdNumber;
        this.loanAmount = loanAmount;
        this.periodType = periodType;
        this.loanPeriod = loanPeriod;
        this.deposit = deposit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    public void setNationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(int loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
}
