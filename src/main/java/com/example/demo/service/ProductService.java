package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Products;
import com.example.demo.model.ProductModel;

public interface ProductService {

	ResponseEntity<?> addProducts(ProductModel pro);

	Products getProductsById(Long di);

	List<Products> getAllProducts(Pageable pageable);

	ResponseEntity<String> deleteProducts(Long di);

	ResponseEntity<String> updateProducts(Products pro, Long di);

}
