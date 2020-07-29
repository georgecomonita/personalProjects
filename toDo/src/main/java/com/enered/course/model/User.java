package com.enered.course.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private List<BaseToDo> items;

    public User(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BaseToDo> getItems() {
        return items;
    }

    public void setItems(List<BaseToDo> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
