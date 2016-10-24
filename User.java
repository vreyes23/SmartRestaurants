package com.example.reyesclan67.smartrestaurants;

/**
 * Created by reyesclan67 on 10/23/2016.
 */

public class User {
    private String firstName;
    private String lastName;

    public void User(String firstName, String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getFullName(){
        return firstName+" "+lastName;
    }

    public void printName(){
        System.out.println(firstName+" "+lastName);
    }


}
