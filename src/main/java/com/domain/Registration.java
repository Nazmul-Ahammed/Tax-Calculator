package com.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "registration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull

    @Column(name = "uname")
    private String uname;


    @NotNull

    @Column(name = "gender")
    private String gender;
    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;
    @NotNull
    @Column(name = "phone")
    private String phone;
    @NotNull
    @Column(name = "passwordd")
    private String passwordd;
    @NotNull
    @Column(name = "varify")
    private Boolean varify;

    public void setPasswordd(String passwordd) {
        this.passwordd = passwordd;
    }

    public Boolean getVarify() {
        return varify;
    }

    public void setVarify(Boolean varify) {
        this.varify = varify;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswordd() {
        return passwordd;
    }

    public void setPassword(String passwordd) {
        this.passwordd = passwordd;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    @NotNull
    @Column(name = "confirm_password")
    private String confirm_password;

}
