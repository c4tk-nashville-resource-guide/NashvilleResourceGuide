package org.guide.controller;

import org.apache.commons.lang3.StringUtils;
import org.guide.domain.Category;
import org.guide.domain.Organization;
import org.guide.exceptions.RecordNotFoundException;
import org.guide.exceptions.ValidationException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rgdavis on 3/11/17.
 */
@RestController
@RequestMapping("organizations")
public class OrganizationController {

    private static Set<Organization> data = new LinkedHashSet<>();

    static {
        data.add(new Organization(UUID.randomUUID().toString(), "Belmont"));
        data.add(new Organization(UUID.randomUUID().toString(), "LifeWay"));
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Organization> getOrganizations() {
        return data.stream()
                .sorted(Comparator.comparing(n -> n.toString()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public Organization getOrganization(@PathVariable String name) {
        Optional<Organization> o = data.stream()
                .filter(  x -> name.equalsIgnoreCase(x.getName()))
                .findFirst();
        if (o.isPresent()) {
            return o.get();
        }
        throw new RecordNotFoundException();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createOrganization(@RequestBody Organization org) {
        if ( !org.validate()) {
            throw new ValidationException();
        }
        data.add(org);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void updateOrganization(@PathVariable String id, @RequestBody Organization org) {
        if (StringUtils.isNotBlank(org.getName())) {
            Optional<Organization> o = data.stream()
                    .filter(  x -> id.equals(x.getId()))
                    .findFirst();
            if (o.isPresent()) {
                Organization orgToChange = o.get();
                orgToChange.setName(org.getName());
            } else {
                throw new RecordNotFoundException();
            }
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteOrganization(@PathVariable String id) {
        Optional<Organization> o = data.stream()
                .filter(  x -> id.equals(x.getId()))
                .findFirst();
        if (o.isPresent()) {
            Organization orgToDelete = o.get();
            data.remove(orgToDelete);
        } else {
            throw new RecordNotFoundException();
        }
    }

}
