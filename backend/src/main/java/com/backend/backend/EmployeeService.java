package com.backend.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Cacheable(value = "employees")  // Cache the list of employees
    public List<Employee> getAllEmployees() {
        logger.info("Service Call findAll emp");
        return employeeRepository.findAll();
    }

    @Cacheable(value = "employee", key = "#id")  // Cache single employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @CachePut(value = "employee", key = "#employee.id")  // Update cache after saving
    @CacheEvict(value = "employees", allEntries = true)  // Invalidate employees
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @CacheEvict(value = "employee", key = "#id")        // Remove single employee cache
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
