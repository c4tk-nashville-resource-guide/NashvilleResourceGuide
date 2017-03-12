package org.guide.delegate;

import org.guide.domain.*;
import org.guide.repository.AddressRepository;
import org.guide.repository.CategoryRepository;
import org.guide.repository.OrganizationRepository;
import org.guide.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by rgdavis on 3/12/17.
 */
@Component
public class ServiceImpl implements ServiceDelegate {

    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<SearchResult> findService(String serviceId, String zip) {

        List<Service> services;
        if (serviceId != null) {
            if ( zip !=null ) {
                services = serviceRepository.findByAddressZipAndCategoryId(zip, serviceId);
            } else {
                services = serviceRepository.findByCategoryId(serviceId);
            }
        } else {
            if (zip != null) {
                services =  serviceRepository.findByAddressZip(zip);
            } else {
                throw new IllegalArgumentException("You have to give me something to work with Dogg.");
            }
        }

        List<Category> categories = categoryRepository.findAll();

        return services.stream()
                .map(service -> {
                    Optional<Category> c = categories.stream()
                            .filter(x -> service.getCategoryId().equals(x.getId()))
                            .findFirst();
                    if (c.isPresent()) {
                        return new SearchResult(service, c.get());
                    } else  {
                        return new SearchResult(service, null);
                    }
                    
                })
                .collect(Collectors.toList());
    }

    @Override
    public void loadRecord(ContributorRecord record) {
        try {
            Organization organization = organizationRepository.findByName(record.getName());
            if ( organization == null ){
                organization = new Organization();
                organization.setId(UUID.randomUUID().toString());
                organization.setName(record.getName());
                organization.setUrl(record.getWebsite());
                organizationRepository.save(organization);
            }
            List<Address> addresses = addressRepository.findByOrganizationId(organization.getId());
            Address address = null;
            if (record.getStreet() != null) {
                Optional<Address> addr = addresses.stream().filter(a -> record.getStreet().equals(a.getStreet())).findFirst();
                if ( addr.isPresent()) {
                    address = addr.get();
                }
            }
            if (address == null) {
                address = new Address();
                address.setId(UUID.randomUUID().toString());
                address.setOrganization(organization);
            }
            address.setStreet(record.getStreet());
            address.setState(record.getState());
            address.setZip(record.getZipcode());
            addressRepository.save(address);
            Service service = new Service();
            service.setId(UUID.randomUUID().toString());
            service.setAddress(address);

            String categoryId = getCategory(record.getCategory());
            service.setCategoryId(categoryId);
            service.setHours(record.getHours());
            serviceRepository.save(service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getCategory(String name) {
        Category cat = categoryRepository.findByName(name);
        if ( cat == null) {
            cat = new Category();
            cat.setName(name);
            cat.setId(UUID.randomUUID().toString());
            categoryRepository.save(cat);
        }
        return cat.getId();
    }


}
