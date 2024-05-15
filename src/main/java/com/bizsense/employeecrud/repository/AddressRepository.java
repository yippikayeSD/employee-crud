package com.bizsense.employeecrud.repository;

import com.bizsense.employeecrud.entity.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    /* Created by SDixit-MSC on 15-05-2024 inside the package - com.bizsense.employeecrud.repository
     *   @Author - SDixit-MSC
     */


    // Fetch all addresses of a particular employee
    List<Address> findByEmployeeId(Long employeeId);
}
