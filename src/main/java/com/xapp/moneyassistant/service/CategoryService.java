package com.xapp.moneyassistant.service;

import com.xapp.moneyassistant.model.Category;

public interface CategoryService {

	Category create(Category c);
	Category update(long id, Category c);
	Category findCategoryById(long id);
	void delete(long id);
	Iterable<Category> getCategories();
	Iterable<Category> findAllByType(String type);

}
