package org.guide.controller;

import org.apache.commons.lang3.StringUtils;
import org.guide.domain.Category;
import org.guide.exceptions.RecordNotFoundException;
import org.guide.exceptions.ValidationException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rgdavis on 3/11/17.
 */
@RestController
@RequestMapping("categories")
public class CategoryController {

    private static Set<Category> data = new LinkedHashSet<>();

    static {
        data.add(new Category(UUID.randomUUID().toString(), "Clothing"));
        data.add(new Category(UUID.randomUUID().toString(), "Food"));
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getCategories() {
        return data.stream()
                .sorted(Comparator.comparing(n -> n.toString()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable String name) {
        Optional<Category> c = data.stream()
                .filter(  x -> name.equalsIgnoreCase(x.getName()))
                .findFirst();
        if (c.isPresent()) {
            return c.get();
        }
        throw new RecordNotFoundException();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createCategory(@RequestBody Category cat) {
        if ( !cat.validate()) {
            throw new ValidationException();
        }
        data.add(cat);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void updateCategory(@PathVariable String id, @RequestBody Category cat) {
        if (StringUtils.isNotBlank(cat.getName())) {
            Optional<Category> c = data.stream()
                    .filter(  x -> id.equals(x.getId()))
                    .findFirst();
            if (c.isPresent()) {
                Category catToChange = c.get();
                catToChange.setName(cat.getName());
            } else {
                throw new RecordNotFoundException();
            }
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable String id) {
            Optional<Category> c = data.stream()
                    .filter(  x -> id.equals(x.getId()))
                    .findFirst();
            if (c.isPresent()) {
                Category catToDelete = c.get();
                data.remove(catToDelete);
            } else {
                throw new RecordNotFoundException();
            }
    }

}
