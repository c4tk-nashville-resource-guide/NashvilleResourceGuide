package org.guide.delegate;

import org.guide.domain.Category;
import org.guide.exceptions.DuplicateException;
import org.guide.exceptions.RecordNotFoundException;
import org.guide.exceptions.ValidationException;
import org.guide.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rgdavis on 3/11/17.
 */
@Component
public class CategoryImpl implements CategoryDelegate {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll(new Sort("name"));
    }

    @Override
    public Category getCategory(String id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void createCategory(Category category) {
        if ( !category.validate()) {
            throw new ValidationException();
        }
        Category existing = categoryRepository.findByName(category.getName());
        if ( existing != null ) {
            throw new DuplicateException();
        }
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(String id, Category category) {
        if ( !category.validate()) {
            throw new ValidationException();
        }
        Category existing = categoryRepository.findOne(id);
        if ( existing == null ) {
            throw new RecordNotFoundException();
        }
        Category dupName = categoryRepository.findByName(category.getName());
        if ( dupName != null && !dupName.getId().equals(id) ) {
            throw new DuplicateException();
        }
        existing.setName(category.getName());
        categoryRepository.save(existing);
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.delete(id);
    }
}
