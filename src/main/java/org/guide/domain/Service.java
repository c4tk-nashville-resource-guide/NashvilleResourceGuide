package org.guide.domain;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

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
    private Address address;
    private Category category;

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

    @ManyToOne
    @JoinColumn( name = "addressid", nullable = false )
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToOne
    @JoinColumn ( name = "categoryid", nullable = false )
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
