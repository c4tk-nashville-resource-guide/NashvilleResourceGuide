package org.guide.repository;

import org.guide.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rgdavis on 3/11/17.
 */
public interface OrganizationRepository extends JpaRepository<Organization, String> {
    Organization findByName(String name);
}
