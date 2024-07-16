package com.aryan.EmployeeRestAPI.controller;

import com.aryan.EmployeeRestAPI.model.Employee;
import com.aryan.EmployeeRestAPI.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    //Create employee rest API
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //Get All employees REST API
    @GetMapping()
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //Get employee by id REST API
    @GetMapping("{id}") // Path variable(Dynamic) http://localhost:8080/api/employees/1(id)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long empId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(empId),HttpStatus.OK);
    }
    //Update employee REST API
    //http://localhost:8080/api/employees/1 (emp id to be updated)
    //Body also needed
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id") long id){
        return new ResponseEntity<>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }
    //Delete employee REST API
    //http://localhost:8080/api/employees/1 (emp id to be updated)
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully",HttpStatus.OK);
    }
}
