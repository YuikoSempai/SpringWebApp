package com.example.springwebapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "audit_log")
public class AuditedMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer id;
    @NotNull
    @Column(name = "invoke_time")
    public String invokeTime;
    @NotNull
    @Column(name = "method_name")
    public String name;

    public AuditedMethod(){

    }

    public AuditedMethod(String invokeTime, String name){
        this.invokeTime = invokeTime;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return invokeTime;
    }

    public void setTime(String time) {
        this.invokeTime = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
