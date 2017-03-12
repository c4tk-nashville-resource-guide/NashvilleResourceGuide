package org.guide.delegate;

import org.guide.domain.Service;
import org.guide.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by rgdavis on 3/12/17.
 */
public class ServiceImpl implements ServiceDelegate {

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public List<Service> findService(String serviceId, String zip) {
        return serviceRepository.findByAddressZipAndCategoryId(zip, serviceId);
    }


}
