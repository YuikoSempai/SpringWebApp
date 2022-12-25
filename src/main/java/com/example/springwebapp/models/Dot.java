package com.example.springwebapp.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


@Entity
@Table(name = "dots")
public class Dot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @NotNull
    @Column(name = "x")
    private double x;
    @NotNull
    @Column(name = "y")
    private double y;
    @NotNull
    @Column(name = "r")
    private int r;
    @NotNull
    @Column(name = "status")
    private String status;

    public Dot() {
    }

    public Dot(double x, double y, int r, String status) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
