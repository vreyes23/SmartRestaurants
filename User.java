package com.example.reyesclan67.smartrestaurants;

/**
 * Created by reyesclan67 on 10/23/2016.
 */

public class User {
    private String firstName;
    private String lastName;
    private String userType;

    //Constructor Method to create User
    public void User(String firstName, String lastName, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
         this.userType = userType;
    }

    public String getFullName(){
        return firstName+" "+lastName;
    }

    public void printName(){
        System.out.println(firstName+" "+lastName);
    }

    @Override
    public String toString(){
        return "First Name: "+firstName+" Last Name: "+lastName+" User Type: "+userType+".";

    }


}
