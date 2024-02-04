package com.amazingJava.RestWebService.restWebService.versioning;

public class PersonV2 {
    private String firstName;
    private String lastName;

    private Name name;
    public PersonV2(Name name) {
        this.firstName = name.getFirstName();
        this.lastName = name.getLastName();
    }

    public String getfirstName() {
        return firstName;
    }

    public String getlastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "PersonV2 [name=" + name + "]";
    }
}
