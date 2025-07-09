package com.organization.employeeservice.controller;

import com.organization.employeeservice.entity.Employee;
import com.organization.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/employee")
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }
    @GetMapping
    public List<Employee> fetchEmployees(){
        return  employeeService.fetchEmployees();
    }
    @GetMapping("/{id}")
    public Employee fetchEmployeeById(@PathVariable int id){
        return employeeService.fetchEmployeeById(id);
    }
}
