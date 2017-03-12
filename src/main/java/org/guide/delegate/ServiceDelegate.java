package org.guide.delegate;

import org.guide.domain.Service;

import java.util.List;

/**
 * Created by rgdavis on 3/12/17.
 */
public interface ServiceDelegate {

    List<Service> findService(String serviceId, String zip);
}
