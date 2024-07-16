package com.aryan.EmployeeRestAPI.service.impl;

import com.aryan.EmployeeRestAPI.exception.ResourceNotFoundException;
import com.aryan.EmployeeRestAPI.model.Employee;
import com.aryan.EmployeeRestAPI.repository.EmployeeRepository;
import com.aryan.EmployeeRestAPI.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee) ;
    }
    @Override
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee=employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else {
            throw new ResourceNotFoundException("Employee","id",id);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //Check whether the employee with given id exists in db
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isEmpty()) {
            throw new ResourceNotFoundException("Employee", "id", id);
        }
        //Update existing employee
        Employee employee1=existingEmployee.get();
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setEmail(employee.getEmail());
        //Save updated employee to database
        employeeRepository.save(employee1);
        return employee1;
    }

    @Override
    public void deleteEmployee(long id) {
        //Check whether employee with given id exists in db
        Optional<Employee> existingEmployee=employeeRepository.findById(id);
        if(existingEmployee.isEmpty()){
            throw new ResourceNotFoundException("Employee","id",id);
        }
        employeeRepository.deleteById(id);
    }
}
