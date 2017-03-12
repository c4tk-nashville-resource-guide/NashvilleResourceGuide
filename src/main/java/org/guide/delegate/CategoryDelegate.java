package org.guide.delegate;

import org.guide.domain.Category;

import java.util.List;

/**
 * Created by rgdavis on 3/11/17.
 */
public interface CategoryDelegate {

    List<Category> findAll();
    Category getCategory(String id);
    void createCategory(Category category);
    void updateCategory(String id, Category category);
    void deleteCategory(String id);
}
