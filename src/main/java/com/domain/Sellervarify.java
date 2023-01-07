package com.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "seller_varify")
public class Sellervarify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull

    @Column(name = "verfied")
    private boolean verfied;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isVerfied() {
        return verfied;
    }

    public void setVerfied(boolean verfied) {
        this.verfied = verfied;
    }


}
