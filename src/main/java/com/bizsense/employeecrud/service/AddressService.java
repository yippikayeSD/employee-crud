package com.bizsense.employeecrud.service;

import com.bizsense.employeecrud.entity.Address;
import com.bizsense.employeecrud.entity.Employee;
import com.bizsense.employeecrud.exception.AddressMismatchException;
import com.bizsense.employeecrud.exception.AddressNotFoundException;
import com.bizsense.employeecrud.exception.EmployeeNotFoundException;
import com.bizsense.employeecrud.repository.AddressRepository;
import com.bizsense.employeecrud.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    /* Created by SDixit-MSC on 15-05-2024 inside the package - com.bizsense.employeecrud.service
     *   @Author - SDixit-MSC
     */

    private final AddressRepository addressRepository;

    private final EmployeeRepository employeeRepository;

    public AddressService(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    // Create Employee Address
    public Address saveAddress(Long employeeId, Address address) {
        Employee employee = getEmployeeById(employeeId);
        address.setEmployee(employee);
        return addressRepository.save(address);
    }

    // Update Employee Address
    public Address updateAddress(Long employeeId, Long addressId, Address addressDetails) {
        Address address = getAddressById(addressId);
        if (!address.getEmployee().getId().equals(employeeId)) {
            throw new AddressMismatchException();
        }
        address.setStreet(addressDetails.getStreet());
        address.setCity(addressDetails.getCity());
        address.setState(addressDetails.getState());
        address.setZipCode(addressDetails.getZipCode());
        return addressRepository.save(address);
    }

    // Fetch all addresses of a particular employee
    public List<Address> getAddressesByEmployeeId(Long employeeId) {
        return addressRepository.findByEmployeeId(employeeId);
    }

    // Helper methods
    private Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException(id);
        }
        return employee.get();
    }

    private Address getAddressById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            throw new AddressNotFoundException(id);
        }
        return address.get();
    }
}
