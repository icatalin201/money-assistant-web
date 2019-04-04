package com.xapp.moneyassistant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xapp.moneyassistant.model.Category;
import com.xapp.moneyassistant.repository.CategoryRepository;
import com.xapp.moneyassistant.service.CategoryService;
import com.xapp.moneyassistant.util.IdMismatchException;
import com.xapp.moneyassistant.util.ItemNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category create(Category c) {
		return categoryRepository.save(c);
	}

	@Override
	public Category update(long id, Category c) {
		if (c.getId() != id) {
			throw new IdMismatchException();
		}
		categoryRepository.findById(id).orElseThrow(ItemNotFoundException::new);
		return categoryRepository.save(c);
	}

	@Override
	public Category findCategoryById(long id) {
		return categoryRepository.findById(id).orElseThrow(ItemNotFoundException::new);
	}

	@Override
	public void delete(long id) {
		categoryRepository.findById(id).orElseThrow(ItemNotFoundException::new);
		categoryRepository.deleteById(id);
	}

	@Override
	public Iterable<Category> getCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Iterable<Category> findAllByType(String type) {
		return categoryRepository.findAllByType(type);
	}
}
