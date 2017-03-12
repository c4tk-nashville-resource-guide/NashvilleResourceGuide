package org.guide.repository;

import org.guide.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rgdavis on 3/11/17.
 */
public interface AddressRepository extends JpaRepository<Address, String>{
    List<Address> findByOrganizationId(String id);
}
