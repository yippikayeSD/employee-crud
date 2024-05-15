package com.bizsense.employeecrud.api;

import com.bizsense.employeecrud.entity.Address;
import com.bizsense.employeecrud.entity.Employee;
import com.bizsense.employeecrud.service.AddressService;
import com.bizsense.employeecrud.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    /* Created by SDixit-MSC on 15-05-2024 inside the package - com.bizsense.employeecrud.api
     *   @Author - SDixit-MSC
     */

    private final EmployeeService employeeService;

    private final AddressService addressService;

    public EmployeeController(EmployeeService employeeService, AddressService addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    // Create new Employee
    @PostMapping
    @Operation(summary = "Create a new Employee")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    // Create Employee Address
    @PostMapping("/{employeeId}/addresses")
    @Operation(summary = "Create a new Address for an Employee")
    public ResponseEntity<Address> createEmployeeAddress(@Min(1) @PathVariable Long employeeId, @Valid @RequestBody Address address) {
        Address savedAddress = addressService.saveAddress(employeeId, address);
        return ResponseEntity.ok(savedAddress);
    }

    // Update Employee
    @PutMapping("/{id}")
    @Operation(summary = "Updates an existing Employee")
    public ResponseEntity<Employee> updateEmployee(@Min(1) @PathVariable Long id, @Valid @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Update Employee Address
    @PutMapping("/{employeeId}/addresses/{addressId}")
    @Operation(summary = "Updates an existing Employee's address")
    public ResponseEntity<Address> updateEmployeeAddress(@Min(1) @PathVariable Long employeeId, @Min(1) @PathVariable Long addressId, @Valid @RequestBody Address addressDetails) {
        Address updatedAddress = addressService.updateAddress(employeeId, addressId, addressDetails);
        return ResponseEntity.ok(updatedAddress);
    }

    // Fetch Employee by ID
    @GetMapping("/{id}")
    @Operation(summary = "Fetches an Employee by ID")
    public ResponseEntity<Employee> getEmployeeById(@Min(1) @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    // Fetch All Employees (include sort and searching Name) using Java Streaming API
    @GetMapping
    @Operation(summary = "Fetches all Employees")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String name) {
        List<Employee> employees = employeeService.getAllEmployees(name);
        return ResponseEntity.ok(employees);
    }

    // Fetch all addresses of a particular employee
    @GetMapping("/{employeeId}/addresses")
    @Operation(summary = "Fetches all Addresses of an Employee")
    public ResponseEntity<List<Address>> getAddressesByEmployeeId(@Min(1) @PathVariable Long employeeId) {
        List<Address> addresses = addressService.getAddressesByEmployeeId(employeeId);
        return ResponseEntity.ok(addresses);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes an Employee")
    public ResponseEntity<Void> deleteEmployee(@Min(1) @PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
