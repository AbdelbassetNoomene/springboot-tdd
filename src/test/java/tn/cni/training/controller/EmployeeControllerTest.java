package tn.cni.training.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tn.cni.training.model.Employee;
import tn.cni.training.service.EmployeeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;


    @Test
    public void getListEmployee() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // given
        Employee employee1 = new Employee(1,"Test1", "Test1", "test1@gmail.com");
        Employee employee2 = new Employee(2,"Test2", "Test2", "test2@gmail.com");
        List<Employee> list = new ArrayList<>();
        list.addAll(Arrays.asList(employee1, employee2));

        // when
        when(employeeService.getListEmployee()).thenReturn(list);

        List<Employee> result = employeeController.getListEmployee();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getFirstName()).isEqualTo(employee1.getFirstName());
        assertThat(result.get(1).getFirstName()).isEqualTo(employee2.getFirstName());
    }

    @Test
    public void addEmployee() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employee = new Employee();
        employee.setId(1);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);

        Employee employeeToAdd = new Employee(1,"Test1", "Test1", "test1@gmail.com");
        ResponseEntity<Employee> responseEntity = employeeController.addEmployee(employeeToAdd);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getId()).isEqualTo(1);
    }
}