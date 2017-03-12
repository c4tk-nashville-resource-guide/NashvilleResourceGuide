package org.guide.delegate;

import org.guide.domain.Address;
import org.guide.domain.Organization;
import org.guide.exceptions.DuplicateException;
import org.guide.exceptions.RecordNotFoundException;
import org.guide.exceptions.ValidationException;
import org.guide.repository.AddressRepository;
import org.guide.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Created by rgdavis on 3/12/17.
 */
public class OrganizationImpl implements OrganizationDelegate {

    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    AddressRepository addressRepository;

    public OrganizationImpl() {}


    @Override
    public Organization findByName(String name) {
        return organizationRepository.findByName(name);
    }

    @Override
    public List<Address> getAddresses(String organizationId) {
        return addressRepository.findByOrganizationId(organizationId);
    }

    @Override
    public void createOrganization(Organization organization) {
        if ( !organization.validate()) {
            throw new ValidationException();
        }

        Organization existingOrganization = organizationRepository.findByName(organization.getName());
        if (existingOrganization != null ) {
            throw new DuplicateException();
        }

        organizationRepository.save(organization);

    }

    @Override
    public void createAddress(String organizationId, Address address) {
        if (!address.validate()) {
            throw new ValidationException();
        }
        Organization organization = organizationRepository.findOne(organizationId);
        if ( organization == null) {
            throw new RecordNotFoundException();
        }
        List<Address> addresses = addressRepository.findByOrganizationId(organizationId);
        Optional<Address> existingAddress = addresses.stream()
                .filter(a -> a.getStreet().equals(address.getStreet()))
                .findFirst();
        if (existingAddress.isPresent()) {
            throw new DuplicateException();
        }
        address.setOrganization(organization);
        addressRepository.save(address);
    }

    @Override
    public void updateAddress(String organizationId, Address address) {
        if (!address.validate()) {
            throw new ValidationException();
        }
        Organization organization = organizationRepository.findOne(organizationId);
        if ( organization == null) {
            throw new RecordNotFoundException();
        }
        List<Address> addresses = addressRepository.findByOrganizationId(organizationId);
        Optional<Address> existingAddress = addresses.stream()
                .filter(a -> (a.getStreet().equals(address.getStreet()) && !a.getId().equals(address.getId())))
                .findFirst();
        if (existingAddress.isPresent()) {
            throw new DuplicateException();
        }
        address.setOrganization(organization);
        addressRepository.save(address);
    }
}
