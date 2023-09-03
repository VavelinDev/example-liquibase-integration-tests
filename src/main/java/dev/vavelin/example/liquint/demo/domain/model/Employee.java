package dev.vavelin.example.liquint.demo.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "first_name")
    @NotEmpty(message = "firstName field cannot be empty!")
    String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "lastName field cannot be empty!")
    String lastName;

    @Column(name = "job_title")
    String jobTitle;

    @Column(name = "hire_date")
    @NotEmpty(message = "hireDate field cannot be empty!")
    LocalDate hireDate;

    @NotEmpty(message = "salary field cannot be empty!")
    BigDecimal salary;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}
