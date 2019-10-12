package tn.cni.training.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tn.cni.training.SpringbootTddApplication;
import tn.cni.training.model.Employee;
import tn.cni.training.service.EmployeeService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringbootTddApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Sql({ "classpath:schema.sql", "classpath:data.sql" })
    @Test
    public void testAllEmployees()
    {
        ResponseEntity<List<Employee>> response = restTemplate.exchange("http://localhost:" + port + "/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {
                });
        assertTrue(response.getStatusCode() == HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(3);


    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee(4,"Test4", "test4", "test4@gmail.com");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/employees", employee, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }
}