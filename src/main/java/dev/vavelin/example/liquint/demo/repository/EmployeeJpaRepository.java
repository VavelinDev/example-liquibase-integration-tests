package dev.vavelin.example.liquint.demo.repository;

import dev.vavelin.example.liquint.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "employee")
public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {
}
