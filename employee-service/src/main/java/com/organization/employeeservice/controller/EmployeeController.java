package com.organization.employeeservice.controller;

import com.organization.employeeservice.entity.Employee;
import com.organization.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/employee")
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }
    
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Employee> fetchEmployees(){
        return  employeeService.fetchEmployees();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Employee fetchEmployeeById(@PathVariable int id){
        return employeeService.fetchEmployeeById(id);
    }

    @GetMapping("/public/info")
    public String publicInfo() {
        return "Public info";
    }
    @GetMapping("/me")
    public String getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        return "Hello, " + jwt.getClaims();
    }
}
