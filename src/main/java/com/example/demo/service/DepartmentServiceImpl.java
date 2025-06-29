package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
	@Override
	public Department findByNameJp(String NameJp) {
		return departmentRepository.findByNameJp(NameJp);
	}

	@Override
	public Department findByNameEn(String NameEn) {
		return departmentRepository.findByNameEn(NameEn);
	}

	@Override
	public void save(Department department) {
		departmentRepository.save(department);
	}

}
