package org.guide.controller;

import org.apache.commons.lang3.StringUtils;
import org.guide.delegate.CategoryDelegate;
import org.guide.domain.Category;
import org.guide.exceptions.RecordNotFoundException;
import org.guide.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rgdavis on 3/11/17.
 */
@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    CategoryDelegate categoryDelegate;

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getCategories() {
        return categoryDelegate.findAll();
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable String name) {
        return categoryDelegate.getCategory(name);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createCategory(@RequestBody Category category) {
        categoryDelegate.createCategory(category);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void updateCategory(@PathVariable String id, @RequestBody Category category) {
        categoryDelegate.updateCategory(id, category);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable String id) {
         categoryDelegate.deleteCategory(id);
    }

}
