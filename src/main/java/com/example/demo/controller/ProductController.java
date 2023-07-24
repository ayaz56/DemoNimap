package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Products;
import com.example.demo.model.ProductModel;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping(value = "products")
public class ProductController {

	@Autowired
	ProductService proService;

	@PostMapping
	public ResponseEntity<?> addProducts(@RequestBody ProductModel pro) {
		return proService.addProducts(pro);
	}

	@GetMapping("{id}")
	public Products getProductsById(@PathVariable Long id) {
		return proService.getProductsById(id);
	}

	@GetMapping
	public List<Products> getAllProducts(Pageable pageable) {
		return proService.getAllProducts(pageable);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProducts(@PathVariable Long id) {
		return proService.deleteProducts(id);
	}

	@PutMapping("{id}")
	public ResponseEntity<String> updateProducts(@RequestBody Products pro, @PathVariable Long id) {
		return proService.updateProducts(pro, id);
	}

}
