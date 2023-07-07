package com.nishchhal.spring.thymeleaf.repository;

import com.nishchhal.spring.thymeleaf.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

