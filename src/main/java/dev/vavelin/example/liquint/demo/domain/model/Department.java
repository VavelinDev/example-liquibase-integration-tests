package dev.vavelin.example.liquint.demo.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue
    Integer id;

    @NotEmpty(message = "name field cannot be empty!")
    String name;

    @Column(name = "manager_id")
    Integer managerId;
    String location;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public String getLocation() {
        return location;
    }
}
