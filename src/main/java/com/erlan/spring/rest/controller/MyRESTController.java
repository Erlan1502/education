package com.erlan.spring.rest.controller;

import com.erlan.spring.rest.entity.Employee;
import com.erlan.spring.rest.exception_handling.EmployeeIncorrectData;
import com.erlan.spring.rest.exception_handling.NoSuchEmployeeException;
import com.erlan.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployee();
        return employeeList;
    }
    @GetMapping("/employees/{id}")
    public Employee showAllEmployees(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);
        if(employee==null){
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in DataBase.");
        }
        return employee;
    }
    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);
        if(employee == null){throw new NoSuchEmployeeException("The employee with id:" + id +" had not found");}
        else{
        employeeService.deleteEmployee(id);}
        return "Was deleted employee with id:" +id;
    }

}
