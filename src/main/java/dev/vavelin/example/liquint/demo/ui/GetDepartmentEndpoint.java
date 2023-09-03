package dev.vavelin.example.liquint.demo.ui;

import dev.vavelin.example.liquint.demo.domain.model.Department;
import dev.vavelin.example.liquint.demo.domain.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class GetDepartmentEndpoint {

    private final DepartmentService service;

    public GetDepartmentEndpoint(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    ResponseEntity<Department> findById(@PathVariable Integer id) {
        Department department = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(department);
    }
}
