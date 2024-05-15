package com.bizsense.employeecrud.exception;

public class AddressMismatchException extends RuntimeException {
    /* Created by SDixit-MSC on 15-05-2024 inside the package - com.bizsense.employeecrud.exception
     *   @Author - SDixit-MSC
     */

    public AddressMismatchException() {
        super("Address does not belong to the specified employee");
    }
}
