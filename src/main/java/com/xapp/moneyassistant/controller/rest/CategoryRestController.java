package com.xapp.moneyassistant.controller.rest;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.moneyassistant.model.Category;
import com.xapp.moneyassistant.model.StandardOutput;
import com.xapp.moneyassistant.service.CategoryService;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping(value = "/create")
	public Category createCategory(@RequestBody Category c) {
		c = categoryService.create(c);
		return c;
	}
	
	@PostMapping(value = "/create/form")
	public StandardOutput createCategoryForm(@ModelAttribute @Valid Category c, Errors e) {
		StandardOutput output = new StandardOutput();
		if (e.hasErrors()) {
			output.setStatus(false);
			Map<String, String> errors = e.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			output.setErrors(errors);
		} else {
			output.setStatus(true);
			c = categoryService.create(c);
			output.setObject(c);
		}
		return output;
	}
	
	@PutMapping(value = "/update/form/{id}")
	public StandardOutput createCategoryForm(@PathVariable long id, @ModelAttribute @Valid Category c, Errors e) {
		StandardOutput output = new StandardOutput();
		if (e.hasErrors()) {
			output.setStatus(false);
			Map<String, String> errors = e.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			output.setErrors(errors);
		} else {
			output.setStatus(true);
			c = categoryService.update(id, c);
			output.setObject(c);
		}
		return output;
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public Boolean deleteCategory(@PathVariable long id) {
		categoryService.delete(id);
		return true;
	}
	
	@PutMapping(value = "/update/{id}")
	public Category updateCategory(@PathVariable long id, @RequestBody Category c) {
		c = categoryService.update(id, c);
		return c;
	}
	
	@GetMapping(value = "/all/{type}")
	public Iterable<Category> getCategoriesByType(@PathVariable String type) {
		return categoryService.findAllByType(type);
	}
	
	@GetMapping(value = "/all")
	public Iterable<Category> getCategories() {
		Iterable<Category> ccs = categoryService.getCategories();
		return ccs;
	}

}
