package com.backend.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        List<Employee> employees = employeeService.getAllEmployees();
        logger.debug("Total employees found: {}", employees.size());
        return employees;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        logger.info("Fetching employee with ID: {}", id);
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            logger.debug("Employee found: {}", employee.get());
            return ResponseEntity.ok(employee.get());
        } else {
            logger.warn("Employee with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        logger.info("Creating new employee: {}", employee.getName());
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        logger.info("Updating employee with ID: {}", id);
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            logger.debug("Existing employee details: {}", employee);
            employee.setName(employeeDetails.getName());
            employee.setPosition(employeeDetails.getPosition());
            employee.setDepartment(employeeDetails.getDepartment());
            Employee updatedEmployee = employeeService.saveEmployee(employee);
            logger.info("Employee updated successfully: {}", updatedEmployee);
            return ResponseEntity.ok(updatedEmployee);
        } else {
            logger.warn("Employee with ID {} not found for update", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        logger.info("Deleting employee with ID: {}", id);
        employeeService.deleteEmployee(id);
        logger.info("Employee with ID {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cache-test")
    public List<Employee> cacheTest() {
        logger.info("Calling /api/employees/cache-test");
        return employeeService.getAllEmployees();
    }
}
