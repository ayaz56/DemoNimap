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
import com.example.demo.repository.CategoryRepository;


@Service
public class ImpCategoryServiceImp implements CategoryService {
	
	@Autowired
	CategoryRepository repo;

	@Override
	public Category addCategory(Category category) {
		return repo.save(category);
	}

	@Override
	public Category getCategoryById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public ResponseEntity<String> deleteCategory(Long id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred while deleting the category",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> updateCategory(Category cat, Long id) {
		try {
			Category existingCategory = repo.findById(id).orElse(null);
            if (existingCategory == null) {
                return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
            }
            existingCategory.setCatName(cat.getCatName());
            repo.save(existingCategory);
            return new ResponseEntity<>("Category updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	@Override
	public List<Category> getAllCategory(Pageable pageable) {
		return repo.findAll(pageable).getContent();
	}

}
