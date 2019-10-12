package tn.cni.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.cni.training.model.Employee;
import tn.cni.training.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("")
    List<Employee> getListEmployee(){
        return employeeService.getListEmployee();
    }

    @PostMapping("")
    ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee empl = employeeService.addEmployee(employee);
        return new ResponseEntity<Employee>(empl,HttpStatus.CREATED);
    }
}
