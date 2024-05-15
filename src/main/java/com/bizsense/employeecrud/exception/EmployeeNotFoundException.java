package com.bizsense.employeecrud.exception;

public class EmployeeNotFoundException extends RuntimeException {
    /* Created by SDixit-MSC on 15-05-2024 inside the package - com.bizsense.employeecrud.exception
     *   @Author - SDixit-MSC
     */

    public EmployeeNotFoundException(Long id) {
        super("Employee not found with id " + id);
    }
}
