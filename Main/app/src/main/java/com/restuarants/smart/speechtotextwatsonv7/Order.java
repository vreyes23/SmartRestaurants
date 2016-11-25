package com.restuarants.smart.speechtotextwatsonv7;

import java.util.ArrayList;

/**
 * I don't think we need this, it was a good to use as a reference but for now let's keep it simple
 * and assume the user could only order items from the menu, in the future we can implement this.
 */

public class Order {

    private String name;
    private double price;
    private ArrayList<String> ingredients;
    //private Image image;

    public Order(String name) {
        this.name = name;
        getFoodDetails(name);
    }

    public void getFoodDetails(String name) {
        String mealtype = name;

        switch (mealtype) {
            case "Hamburger":
                this.ingredients.add("Toasted Buns");
                this.ingredients.add("Onion");
                this.ingredients.add("Beef Patty");
                this.ingredients.add("Lettuce");
                this.ingredients.add("Tomato");
                this.ingredients.add("Spread");
                this.price = 2.10;
                break;
            case "Cheeseburger":
                this.ingredients.add("Toasted Buns");
                this.ingredients.add("Onion");
                this.ingredients.add("Cheese Slice");
                this.ingredients.add("Beef Patty");
                this.ingredients.add("Lettuce");
                this.ingredients.add("Tomato");
                this.ingredients.add("Spread");
                this.price = 2.40;
                break;
            case "Double-Double":
                this.ingredients.add("Toasted Buns");
                this.ingredients.add("Cheese Slice");
                this.ingredients.add("Beef Patty");
                this.ingredients.add("Onion");
                this.ingredients.add("Cheese Slice");
                this.ingredients.add("Beef Patty");
                this.ingredients.add("Lettuce");
                this.ingredients.add("Tomato");
                this.ingredients.add("Spread");
                this.price = 3.45;
                break;
            case "French Fries":
                this.ingredients.add("French Fries");
                this.price = 1.60;
                break;
            case "Shake":
                this.ingredients.add("Chocolate");
                this.ingredients.add("Vanilla");
                this.ingredients.add("Strawberry");
                this.price = 2.15;
                break;
            case "Drink":
                this.ingredients.add("Coca-Cola Classic");
                this.ingredients.add("Diet Coke");
                this.ingredients.add("7UP");
                this.ingredients.add("Dr. Pepper");
                this.ingredients.add("Root Beer");
                this.ingredients.add("Lemonade");
                this.ingredients.add("Minute Maid Light Lemonade");
                this.ingredients.add("Iced Tea");
                this.ingredients.add("Coffee");
                this.price = 1.65;
                break;
            default:
                throw new IllegalArgumentException("Invalid Food Item");
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
