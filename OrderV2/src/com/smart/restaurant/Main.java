package com.smart.restaurant;

import java.lang.reflect.Array;



public class Main {

    public static void main(String[] args) {

        // Print the menu to the UI
        // Call the Food Class from here. 
        // Divide the Menu as the following:
        //  | Restaurant Name | Main Menu | Sub Menu | Drinks | Sides |

        // When customer is recognized by a camera detector[Future] we,
        // Call the Text to Speech Class to Greet the customer
        TextToSpeech tts = new TextToSpeech();
        tts.speak("Welcome to XYZ Restaurant!");

        // If customer presses the "Voice" icon button on the UI then the following occurs
        VoiceToText vts = new VoiceToText();
        String listener = "Hi, I would like a burger with no onions an oreo milkshake, fries and a drink";
        String rawText = vts.listen(listener); // returns raw data, below is an example.

        MachineLearning ml = new MachineLearning();
        String convertedToText =  ml.getKeyWords(rawText); // Returns main keywords identify by the ML algorithm

        //Create Order
        Order order = new Order(convertedToText); // Create as many object orders we need, each order is an object.

        // Call Watson and confirm if the order is correct //
        tts.speak("Is the order correct? " + order);
        vts.listen("Yes");
        tts.speak("Your order is: " + order.getTotal() + "pay in cash or credit?");
        vts.listen("Credit");


    }
}
