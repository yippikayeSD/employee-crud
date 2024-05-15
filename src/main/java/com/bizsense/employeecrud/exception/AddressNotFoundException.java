package com.bizsense.employeecrud.exception;

public class AddressNotFoundException extends RuntimeException {
    /* Created by SDixit-MSC on 15-05-2024 inside the package - com.bizsense.employeecrud.exception
     *   @Author - SDixit-MSC
     */

    public AddressNotFoundException(Long id) {
        super("Address not found with id " + id);
    }
}

