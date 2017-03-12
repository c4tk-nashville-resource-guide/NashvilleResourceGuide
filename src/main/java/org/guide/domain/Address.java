package org.guide.domain;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * Created by rgdavis on 3/11/17.
 */

@Entity
@Table(name = "address")
public class Address {

    @Id
    private String id;
    private Organization organization;
    private String street;
    private String city;
    private String state;
    private String zip;
    private Set<Service> services;

    public Address() {
    }

    public Address(String id, Organization organization, String street, String city, String state, String zip) {
        this.id = id;
        this.organization = organization;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "organizationid", nullable = false)
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @OneToMany(mappedBy = "service")
    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public boolean validate() {
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        if (StringUtils.isEmpty(organization)) {
            return false;
        }
        return true;
    }
}
