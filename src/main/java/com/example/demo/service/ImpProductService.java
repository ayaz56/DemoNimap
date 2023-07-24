package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Products;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class ImpProductService implements ProductService {

	@Autowired
	ProductRepository repo;

	@Autowired
	CategoryRepository catrepo;

	@Override
	public ResponseEntity<?> addProducts(ProductModel pro) {
		Category updatedCategory = catrepo.findById(pro.getCatId()).orElse(null);
		if (updatedCategory == null) {
			return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
		}
		Products newProduct = new Products();
		newProduct.setProDesc(pro.getProDesc());
		newProduct.setProName(pro.getProName());
		newProduct.setProPrice(pro.getProPrice());
		newProduct.setCategory(updatedCategory);
		return new ResponseEntity<>(repo.save(newProduct), HttpStatus.OK);
	}

	@Override
	public Products getProductsById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<Products> getAllProducts(Pageable pageable) {
		List<Products> pro =repo.findAll(pageable).getContent();
		pro.forEach(pros-> pros.setCategoryName(pros.getCategory().getCatName()));
		return repo.findAll(pageable).getContent();
	}

	@Override
	public ResponseEntity<String> deleteProducts(Long di) {
		try {
			repo.deleteById(di);
			return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred while deleting the product",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<String> updateProducts(Products pro, Long di) {
		try {
			Products existingProduct = repo.findById(di).orElse(null);
			if (existingProduct == null) {
				return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
			}

			Category updatedCategory = catrepo.findById(pro.getCategory().getCatId()).orElse(null);
			if (updatedCategory == null) {
				return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
			}
			existingProduct.setProName(pro.getProName());
			existingProduct.setCategory(updatedCategory);

			repo.save(existingProduct);

			return new ResponseEntity<>("Prol̥duct updated successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred while updating the product",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
