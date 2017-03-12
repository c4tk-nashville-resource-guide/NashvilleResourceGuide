package org.guide.delegate;

import org.guide.domain.Address;
import org.guide.domain.Organization;

import java.util.List;

/**
 * Created by rgdavis on 3/12/17.
 */
public interface OrganizationDelegate {

    Organization findByName(String name);
    List<Address> getAddresses(String organizationId);
    void createOrganization(Organization organization);
    void createAddress(String organizationId, Address address);
    void updateAddress(String organizationId, Address address);
}
