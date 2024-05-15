package com.bizsense.employeecrud.service;

import com.bizsense.employeecrud.entity.Employee;
import com.bizsense.employeecrud.exception.EmployeeNotFoundException;
import com.bizsense.employeecrud.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    /* Created by SDixit-MSC on 15-05-2024 inside the package - com.bizsense.employeecrud.service
     *   @Author - SDixit-MSC
     */
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Create new Employee
    public Employee saveEmployee(Employee employee) {
        // Set the employee for each address
        employee.getAddresses().forEach(address -> address.setEmployee(employee));
        return employeeRepository.save(employee);
    }

    // Update Employee
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        return employeeRepository.save(employee);
    }

    // Fetch Employee by ID
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException(id);
        }
        return employee.get();
    }

    //  Fetch All Employees (include sort and searching Name) using Java Streaming API
    public List<Employee> getAllEmployees(String name) {
        List<Employee> employees = employeeRepository.findAll();
        if (name != null && !name.isEmpty()) {
            employees = employees.stream()
                    .filter(e -> e.getFirstName().toLowerCase().contains(name.toLowerCase()) || e.getLastName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }
        return employees.stream()
                .sorted((e1, e2) -> e1.getFirstName().compareToIgnoreCase(e2.getFirstName()))
                .toList();
    }

    // Delete Employee
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }
}
