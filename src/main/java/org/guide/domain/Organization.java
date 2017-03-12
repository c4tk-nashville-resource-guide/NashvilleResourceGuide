package org.guide.domain;

import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by rgdavis on 3/11/17.
 */

@Entity
@Table(name = "organization")
public class Organization {
    @Id
    private String id;
    private String name;
    private String url;
    @OneToMany(mappedBy = "organization")
    private Set<Address> addresses;

    public Organization() {
    }

    public Organization(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Address> getAddresses() {
        if ( addresses == null) {
            addresses = new LinkedHashSet<>();
        }

        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public boolean validate() {
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return true;
    }
}
