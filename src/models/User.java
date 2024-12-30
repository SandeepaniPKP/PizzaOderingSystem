package models;

import java.util.ArrayList;
import java.util.List;
import models.Pizza;

/**
 * User class represents a customer with their profile and loyalty points.
 */
public class User {
    private String name;
    private List<Pizza> favorites;
    private int loyaltyPoints;

    public User(String name) {
        this.name = name;
        this.favorites = new ArrayList<>();
        this.loyaltyPoints = 0;
    }

    public String getName() {
        return name;
    }

    public List<Pizza> getFavorites() {
        return favorites;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
        System.out.println(points + " loyalty points added! Total points: " + loyaltyPoints);
    }

    public void redeemPoints(int points) {
        if (loyaltyPoints >= points) {
            loyaltyPoints -= points;
            System.out.println(points + " points redeemed! Remaining points: " + loyaltyPoints);
        } else {
            System.out.println("Not enough points to redeem.");
        }
    }

    public void addFavorite(Pizza pizza) {
        this.favorites.add(pizza);
        System.out.println("Pizza added to favorites!");
    }
}
