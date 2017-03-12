package org.guide.domain;

import org.hibernate.annotations.ManyToAny;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by MaryBeth on 3/11/17.
 */

@Entity
@Table(name = "service")
public class Service {

    @Id
    private String id;
    private String hours;
    private String notes;
    @ManyToOne
    @JoinColumn( name = "addressid", nullable = false )
    private Address address;
    @Column(name = "categoryid")
    private String categoryId;

    public Service() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean validate() {
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        if (StringUtils.isEmpty(address)) {
            return false;
        }
        if (StringUtils.isEmpty(categoryId)) {
            return false;
        }
        return true;
    }
}
