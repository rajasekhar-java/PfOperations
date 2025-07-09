package com.organization.employeeservice.service;

import com.organization.employeeservice.entity.Employee;
import com.organization.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public List<Employee> fetchEmployees(){
        return employeeRepository.findAll();
    }
    public Employee fetchEmployeeById(int id){
        return employeeRepository.findById(id).orElse(null);
    }
}
