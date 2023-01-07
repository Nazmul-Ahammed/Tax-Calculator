package com.domain;

import javax.persistence.*;

@Entity
@Table(name="variables")
public class Variables {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String basic_salary;

    private String house_rent;

    private String medical;
    @Column(name="conve")

    private String con;
    @Column(name="festibal")

    private String festival;

    private String investment;
    private char category;
    private char zone;
    private String taxble_income;
    private String total_tax;
    private String m_tax;

    public Long getId() {
        return id;
    }
    @ManyToOne
    @JoinColumn(name = "userid")
    private Adminuser adminUser;

    public Adminuser getUser() {
        return adminUser;
    }

    public void setUser(Adminuser adminUser) {
        this.adminUser = adminUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxble_income() {
        return taxble_income;
    }

    public void setTaxble_income(String taxble_income) {
        this.taxble_income = taxble_income;
    }

    public String getTotal_tax() {
        return total_tax;
    }

    public void setTotal_tax(String total_tax) {
        this.total_tax = total_tax;
    }

    public String getM_tax() {
        return m_tax;
    }

    public void setM_tax(String m_tax) {
        this.m_tax = m_tax;
    }

    public String getBasic_salary() {
        return basic_salary;
    }

    public void setBasic_salary(String basic_salary) {
        this.basic_salary = basic_salary;
    }

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival;
    }



    public String getInvestment() {
        return investment;
    }

    public void setInvestment(String investment) {
        this.investment = investment;
    }

    public char getZone() {
        return zone;
    }

    public void setZone(char zone) {
        this.zone = zone;
    }

    public char getCategory() {
        return category;
    }

    public void setCategory(char category) {
        this.category = category;
    }

    public String getHouse_rent() {
        return house_rent;
    }

    public void setHouse_rent(String house_rent) {
        this.house_rent = house_rent;
    }
}
