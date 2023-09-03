package dev.vavelin.example.liquint.demo.ui;

import dev.vavelin.example.liquint.demo.domain.model.Employee;
import dev.vavelin.example.liquint.demo.domain.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class GetEmployeeEndpoint {

    private final EmployeeService service;

    public GetEmployeeEndpoint(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    ResponseEntity<Employee> findById(@PathVariable Integer id) {
        Employee employee = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
}
