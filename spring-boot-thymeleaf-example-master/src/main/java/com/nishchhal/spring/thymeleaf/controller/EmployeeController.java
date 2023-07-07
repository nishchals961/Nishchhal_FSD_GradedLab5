package com.nishchhal.spring.thymeleaf.controller;

import com.nishchhal.spring.thymeleaf.entity.Employee;
import com.nishchhal.spring.thymeleaf.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {
  @Autowired
  private EmployeeRepository employeeRepository;

  @GetMapping("/")
  public String viewAllEmployees(Model model) {
    model.addAttribute("employees", employeeRepository.findAll());
    return "employee-list";
  }

  @GetMapping("/employee/new")
  public String showEmployeeForm(Model model) {
    model.addAttribute("employee", new Employee());
    return "employee-form";
  }

  @PostMapping("/employee/new")
  public String saveEmployee(@ModelAttribute("employee") Employee employee) {
    employeeRepository.save(employee);
    return "redirect:/";
  }

  @GetMapping("/employee/edit/{id}")
  public String showEditForm(@PathVariable("id") Long id, Model model) {
    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id: " + id));
    model.addAttribute("employee", employee);
    return "employee-form";
  }

  @PostMapping("/employee/update/{id}")
  public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee) {
    Employee existingEmployee = employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id: " + id));
    existingEmployee.setFirstName(employee.getFirstName());
    existingEmployee.setLastName(employee.getLastName());
    existingEmployee.setDesignation(employee.getDesignation());
    employeeRepository.save(existingEmployee);
    return "redirect:/";
  }

  @GetMapping("/employee/delete/{id}")
  public String deleteEmployee(@PathVariable("id") Long id) {
    Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id: " + id));
    employeeRepository.delete(employee);
    return "redirect:/";
  }
}
