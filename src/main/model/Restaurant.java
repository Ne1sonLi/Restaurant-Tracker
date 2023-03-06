package model;

import org.json.JSONObject;

// Represents a restaurant with a name, rating, location, cuisine type, and rating
public class Restaurant {

    private final String name;
    private final String location;
    private final String cuisine;
    private int rating;

    // REQUIRES: name and loc to have non-zero length
    // EFFECTS: constructs a Restaurant with the given name, location, cuisine type, and a rating of -1
    public Restaurant(String name, String loc, String cuisine) {
        this.name = name;
        this.location = loc;
        this.cuisine = cuisine;
        this.rating = -1; // will only be a positive integer when added to favourites list
    }

    // REQUIRES: 0 <= rating <= 10
    // MODIFIES: this
    // EFFECTS: sets/changes the rating of a restaurant when it is added to favourites list
    public void setRating(int rating) {
        this.rating = rating;
    }

    // EFFECTS: returns the name of the restaurant
    public String getName() {
        return name;
    }

    // EFFECTS: returns the location (address) of the restaurant
    public String getLocation() {
        return location;
    }

    // EFFECTS: returns the cuisine type of the restaurant
    public String getCuisine() {
        return cuisine;
    }

    // REQUIRES: restaurant must be in the favourites list
    // EFFECTS: returns the rating of the restaurant
    public int getRating() {
        return rating;
    }

    // EFFECTS: returns this as a JSON object
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", this.name);
        jsonObject.put("location", this.location);
        jsonObject.put("cuisine", cuisine);
        jsonObject.put("rating", this.rating);
        return jsonObject;
    }
}
