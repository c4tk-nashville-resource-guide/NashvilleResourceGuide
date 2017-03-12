package org.guide.repository;

import org.guide.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rgdavis on 3/11/17.
 */
public interface ServiceRepository extends JpaRepository<Service, String> {

    List<Service> findByAddressZip(String zip);
    List<Service> findByCategoryId(String categoryId);
    List<Service> findByAddressZipAndCategoryId(String zip, String categoryId);

}
