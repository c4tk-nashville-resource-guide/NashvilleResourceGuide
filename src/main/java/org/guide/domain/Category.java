package org.guide.domain;

import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * Created by rgdavis on 3/11/17.
 */
public class Category {
    private String id;
    private String name;
    private int count;

    public Category() {}
    public Category(String id, String name) {
        this.id = id;
        this.name = name;
        this.count = 0;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
