package com.smart.restaurant;

import java.awt.*;

public class Food {

    private String name;
    private double price;
    private Image image;

    public static final Food[] FOOD_OBJECTS =
            {       new Food("Burger", 5.99, "Burger.jpg"),
                    new Food("Chicken Sanwhich", 5.99, "Chicken_Sanwhich.jpg"),
                    new Food("Chicken Nuggets", 3.99, "Chicken_Nuggets.jpg"),
                    new Food("Fries", 3.99, "Fries.jpg"),
                    new Food("Milkshake", 4.99, "Milkshake.jpg"),
                    new Food("Sundae", 3.99, "Sundae.jpg"),
            };
    private Food(String name, double price, String imageName) {
        this.name = name;
        this.price = price;
        this.image = Toolkit.getDefaultToolkit().getImage(imageName);

		/*  Java normally loads images in a background thread.
		 *  This waits for the image to finish loading before moving on with
		 *  the rest of the program.  That helps to keep things
		 *  synchronized properly.
		 */
        try {
            MediaTracker tracker = new MediaTracker(new Panel());
            tracker.addImage(image, 0);
            tracker.waitForID(0);
            if (tracker.statusID(0,true) != MediaTracker.COMPLETE) {
                throw new RuntimeException("Unable to load " + imageName);
            }
        } catch(InterruptedException e) {
            // won't be interrupted, so no worries :-)
        }
    }

    /**
     * Getter for the Image associated with this food.  (It's a very small
     * picture of the food item.)
     *
     * @return a picture representing this item
     */
    public Image getImage() {
        return image;  // technically, this is a privacy leak!  :-)
    }

    /**
     * Getter for the name of this food.
     *
     * @return the name of this food
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the price cost of this food.
     *
     * @return price cost for this food.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Checks if the current object is equal to the parameter.  Note:
     * only the NAMES of the foods are compared.  If the two foods have
     * the same name, they are considered equal!
     *
     * @param other Food item to be compared with the current object
     * @return true if the two Foods have the same name, false otherwise
     */
    public boolean equals(Food other) {
        return (name.equals(other.name));
    }

    /**
     * Returns the name of the food.
     *
     * @return the name of the food
     */
    public String toString() {
        return name;
    }
}
