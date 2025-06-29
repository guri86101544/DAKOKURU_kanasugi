package com.example.demo.service;

import com.example.demo.entity.Department;

public interface DepartmentService {

	Department findByNameJp(String NameJp);
	Department findByNameEn(String NameEn);
	public void save(Department department);

}
