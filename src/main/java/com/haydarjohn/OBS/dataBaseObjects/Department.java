package com.haydarjohn.OBS.model;

import jakarta.persistence.*;

@Entity
@Table(name = "department", schema = "obs_database", indexes = {
        @Index(name = "fk_head_department", columnList = "head_of_department")
})
public class Department {
    @Id
    @Column(name = "department_id", nullable = false)
    private Integer id;

    @Column(name = "dep_name", nullable = false, length = 50)
    private String depName;

    @Column(name = "head_of_department", length = 10)
    private String headOfDepartment;

    @Column(name = "budget")
    private Integer budget;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

}