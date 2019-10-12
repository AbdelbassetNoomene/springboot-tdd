package tn.cni.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.cni.training.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
