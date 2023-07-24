package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {

	@Autowired
	CategoryService catService;

	@PostMapping
	public Category addCategory(@RequestBody Category cat) {
		return catService.addCategory(cat);
	}

	@GetMapping("{id}")
	public Category getCategoryById(@PathVariable Long id) {
		return catService.getCategoryById(id);
	}

	@GetMapping
	public List<Category> getAllCategory(Pageable pageable) {
		return catService.getAllCategory(pageable);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String>  deleteCategory(@PathVariable Long id) {
		return catService.deleteCategory(id);
	}

	@PutMapping("{id}")
	public ResponseEntity<String>  updateCategory(@RequestBody Category cat, @PathVariable Long id) {
		return catService.updateCategory(cat, id);
	}

}
