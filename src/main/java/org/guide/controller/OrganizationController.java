package org.guide.controller;

import org.apache.commons.lang3.StringUtils;
import org.guide.delegate.OrganizationDelegate;
import org.guide.domain.Address;
import org.guide.domain.Category;
import org.guide.domain.Organization;
import org.guide.exceptions.RecordNotFoundException;
import org.guide.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rgdavis on 3/11/17.
 */
@RestController
@RequestMapping("organizations")
public class OrganizationController {

    @Autowired
    OrganizationDelegate organizationDelegate;


    @RequestMapping(value="{organizationName}", method = RequestMethod.GET)
    public Organization getOrganization(@PathVariable String organizationName) {
        return organizationDelegate.findByName(organizationName);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createOrganization(@RequestBody Organization organization) {
        organizationDelegate.createOrganization(organization);
    }

//    @RequestMapping(value = "{organizationId}", method = RequestMethod.PUT)
//    public void updateAddress(@PathVariable String organizationId, @RequestBody Address address) {
//        organizationDelegate.updateAddress(organizationId, address);
//    }

}
