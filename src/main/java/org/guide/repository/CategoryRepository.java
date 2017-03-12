package org.guide.repository;

import org.guide.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rgdavis on 3/11/17.
 */
public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByName(String name);
}
