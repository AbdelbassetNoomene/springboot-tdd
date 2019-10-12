package tn.cni.training.service;

import tn.cni.training.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getListEmployee();

    Employee addEmployee(Employee employee);
}
