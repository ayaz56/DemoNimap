package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Category;

public interface CategoryService {

	public Category addCategory(Category addCategory);

	public Category getCategoryById(Long id);

	public ResponseEntity<String> deleteCategory(Long id);

	public ResponseEntity<String> updateCategory(Category cat, Long id);

	public List<Category> getAllCategory(Pageable pageable);
}
