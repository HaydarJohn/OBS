package com.haydarjohn.OBS.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "classroom", schema = "obs_database")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id", nullable = false)
    private String id;


    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "building", length = 50)
    private String building;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}