package tn.cni.training.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tn.cni.training.model.Employee;
import tn.cni.training.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void getListEmployee() {
        // given
        Employee employee1 = new Employee(1,"Test1", "Test1", "test1@gmail.com");
        Employee employee2 = new Employee(2,"Test2", "Test2", "test2@gmail.com");
        List<Employee> list = new ArrayList<>();
        list.addAll(Arrays.asList(employee1, employee2));

        // when
        when(employeeRepository.findAll()).thenReturn(list);

        List<Employee> result = employeeService.getListEmployee();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getFirstName()).isEqualTo(employee1.getFirstName());
        assertThat(result.get(1).getFirstName()).isEqualTo(employee2.getFirstName());
    }

    @Test
    void addEmployee() {
        // given
        Employee employee = new Employee(1,"Test1", "Test1", "test1@gmail.com");

        // when
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee  savedEmployee = employeeService.addEmployee(employee);

        // then
        assertThat(savedEmployee.getFirstName()).isEqualTo(employee.getFirstName());
    }
}