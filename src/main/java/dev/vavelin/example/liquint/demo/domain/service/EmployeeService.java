package dev.vavelin.example.liquint.demo.domain.service;

import dev.vavelin.example.liquint.demo.domain.exception.DemoAppErrorCode;
import dev.vavelin.example.liquint.demo.domain.exception.EmployeeNotFoundException;
import dev.vavelin.example.liquint.demo.domain.model.Employee;
import dev.vavelin.example.liquint.demo.domain.repository.EmployeeJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeJpaRepository repository;

    public EmployeeService(EmployeeJpaRepository repository) {
        this.repository = repository;
    }

    public Employee findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(DemoAppErrorCode.ID_NOT_EXIST, "Employee with the given id not found"));
    }
}
