package dev.vavelin.example.liquint.demo.domain.repository;

import dev.vavelin.example.liquint.demo.domain.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentJpaRepository extends JpaRepository<Department, Integer> {
}
