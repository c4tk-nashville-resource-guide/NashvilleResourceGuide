package org.guide.domain;

/**
 * Created by rgdavis on 3/12/17.
 */
public class SearchResult {

    private String name;
    private String street;
    private String zipcode;
    private String city;
    private String state;
    private String website;
    private String hours;
    private String categoryId;
    private String categoryName;

    public SearchResult(){}

    public SearchResult(Service service, Category category) {
        name = service.getAddress().getOrganization().getName();
        street = service.getAddress().getStreet();
        zipcode = service.getAddress().getZip();
        state = service.getAddress().getState();
        website = service.getAddress().getOrganization().getUrl();
        hours = service.getHours();
        if (category != null){
            categoryId = category.getId();
            categoryName = category.getName();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
