package com.sahilKadbe.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahilKadbe.springboot.exception.ResourceNotFoundException;
import com.sahilKadbe.springboot.model.Employee;
import com.sahilKadbe.springboot.repository.EmployeeRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// get all employees
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found " + id));

		return ResponseEntity.ok(employee);
	}

	// update employees details
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployees(@PathVariable long id, @RequestBody Employee employeesdetails) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found " + id));

		employee.setFirstName(employeesdetails.getFirstName());
		employee.setLastName(employeesdetails.getLastName());
		employee.setEmailId(employeesdetails.getEmailId());

		Employee updatingEmployeeDetails = employeeRepository.save(employee);
		return ResponseEntity.ok(updatingEmployeeDetails);

	}

	// delete the requested employee details
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found" + id));
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
