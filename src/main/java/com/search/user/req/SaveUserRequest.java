package com.search.user.req;

public class SaveUserRequest {
    private String name;
    private int age;

    public SaveUserRequest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
