package dev.vavelin.example.liquint.demo.domain.service;

import dev.vavelin.example.liquint.demo.domain.exception.DemoAppErrorCode;
import dev.vavelin.example.liquint.demo.domain.exception.DepartmentNotFoundException;
import dev.vavelin.example.liquint.demo.domain.model.Department;
import dev.vavelin.example.liquint.demo.domain.repository.DepartmentJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentJpaRepository repository;

    public DepartmentService(DepartmentJpaRepository repository) {
        this.repository = repository;
    }

    public Department findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(DemoAppErrorCode.ID_NOT_EXIST, "Department with the given id not found"));
    }
}
