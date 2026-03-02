package com.uniovi.sdi.sdi2526902spring.repositories;

import com.uniovi.sdi.sdi2526902spring.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByCode(String code);
}
