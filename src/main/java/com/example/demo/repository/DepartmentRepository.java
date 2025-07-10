package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByNameJp(String NameJp);
	Department findByNameEn(String NameEn);
	boolean existsByNameJp(String value);
	Optional<Department> findById(Long id);
	void deleteById(Long id);

}
