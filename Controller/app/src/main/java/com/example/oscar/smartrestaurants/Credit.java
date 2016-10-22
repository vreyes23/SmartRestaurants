package com.example.oscar.smartrestaurants;

/**
 * Created by Oscar on 10/8/16.
 */
public class Credit extends Payment {
    private String creditcard;
    private String cvs;
    public String getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }

    public String getCvs() {
        return cvs;
    }

    public void setCvs(String cvs) {
        this.cvs = cvs;
    }
}
