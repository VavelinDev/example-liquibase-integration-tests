package dev.vavelin.example.liquint.demo.domain.repository;

import dev.vavelin.example.liquint.demo.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {
}
