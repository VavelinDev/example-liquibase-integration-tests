package dev.vavelin.example.liquint.demo.repository;

import dev.vavelin.example.liquint.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "department")
public interface DepartmentJpaRepository extends JpaRepository<Department, Integer> {
}
