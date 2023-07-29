package com.sahilKadbe.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahilKadbe.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
