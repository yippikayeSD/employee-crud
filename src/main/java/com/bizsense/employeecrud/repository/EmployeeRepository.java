package com.bizsense.employeecrud.repository;

import com.bizsense.employeecrud.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /* Created by SDixit-MSC on 15-05-2024 inside the package - com.bizsense.employeecrud.repository
     *   @Author - SDixit-MSC
     */

    //Fetch employees by name sorted
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:name% OR e.lastName LIKE %:name% ORDER BY e.firstName ASC, e.lastName ASC")
    List<Employee> findByNameSorted(@Param("name") String name);
}
