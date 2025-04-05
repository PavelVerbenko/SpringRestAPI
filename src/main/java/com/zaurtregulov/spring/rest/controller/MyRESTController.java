package com.zaurtregulov.spring.rest.controller;

import com.zaurtregulov.spring.rest.exception_handing.EmployeeIncorrectData;
import com.zaurtregulov.spring.rest.exception_handing.NoSuchEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import com.zaurtregulov.spring.rest.entity.Employee;
import com.zaurtregulov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/employees")
    @Transactional
    public List<Employee> showAllEmployees() {
        List<Employee> AllEmployees = employeeService.getAllEmployees();
        return AllEmployees;
    }

    @GetMapping("/employees/{id}")
    @Transactional
    public Employee getEmployees(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID  = " + " in Database");
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID  = " + " in Database");
        }


        employeeService.deleteEmployee(id);
        return "Employee with ID = " + id + " was deleted";
    }
}
