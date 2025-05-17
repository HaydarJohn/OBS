package com.haydarjohn.OBS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "classroom", schema = "obs_database")
public class Classroom {
    @Id
    @Column(name = "classroom_id", nullable = false, length = 10)
    private String classroomId;

    @Column(name = "building", nullable = false, length = 50)
    private String building;

    @Column(name = "room_number", nullable = false, length = 10)
    private String roomNumber;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "has_projector", length = 3)
    private String hasProjector;

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(String hasProjector) {
        this.hasProjector = hasProjector;
    }

}