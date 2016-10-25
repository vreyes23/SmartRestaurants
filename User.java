package com.example.reyesclan67.smartrestaurants;

import java.util.UUID;

/**
 * Created by Victor Reyes last updated on 10/23/2016.
 *Smart Restaurants Application
 *For CS465 Applied Cognitive Computing
 */

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private int userType;
    private String[] categories={"Cook", "Manager", "Owner", "Administrator"};
    private String username;
    private String passcode;


    //Constructor Method to create User
    public User(String firstName, String lastName, int userType, String username, String passcode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.username=username;
        this.passcode =passcode;
        this.id = UUID.randomUUID().toString();


    }

    //This method returns the First Name of the User
    public String getFirstName() {
        return firstName;
    }

    //This method returns the Last Name of the User
    public String getLastName() {
        return lastName;
    }

    //This method returns the type of the User
    public String getUserType() {
        return categories[userType];
    }

    //This method returns the ID of the User
    public String getId() {
        return id;
    }
    //This methods allows us to change the type of User the current user is
    public void setUserType(int userType) {
         this.userType = userType;
    }

    //This method returns the full name of the User
    public String getFullName(){
        return firstName+" "+lastName;
    }

    //This method prints out the full name of the User
    public void printName(){
        System.out.println(firstName+" "+lastName);
    }

    //This method Overrides the toString method to return the data on the user
    @Override
    public String toString(){
        return "First Name: "+firstName+" Last Name: "+lastName+" User Type: "+categories[userType]+" id: "+id+" ";

    }


}
