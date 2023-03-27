package model;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of restaurants that the user can browse

public class BrowseRestaurants {

    private final List<Restaurant> browseRestaurants;

    public BrowseRestaurants() {
        browseRestaurants = new ArrayList<>();
    }

    // EFFECTS: adds given restaurant to the browse collection if it is not already in collection
    public void addBrowseRestaurants(Restaurant r) {
        if (!containsBrowseRestaurant(r.getName())) {
            browseRestaurants.add(r);
        }
    }

    // EFFECTS: return true is restaurant name is in browse collection, false otherwise
    public boolean containsBrowseRestaurant(String name) {
        for (Restaurant r : browseRestaurants) {
            if (r.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: name must be the name of a restaurant in browse collection
    // EFFECTS: returns the index the restaurant with the given name
    public int searchBrowseRestaurants(String name) {
        int index = 0;
        for (int i = 0; i < getNumRestaurants(); i++) {
            if (name.equals(browseRestaurants.get(i).getName())) {
                index = i;
            }
        }
        return index;
    }

    // EFFECTS: returns the number of restaurants currently in browse collection
    public int getNumRestaurants() {
        return browseRestaurants.size();
    }

    // REQUIRES: 0 <= index < getNumRestaurants()
    // EFFECTS: returns the restaurant at the given index in browse collection
    public Restaurant getRestaurant(int index) {
        return browseRestaurants.get(index);
    }

    // EFFECTS: returns a list of restaurants in the browse collection
    public List<Restaurant> getBrowseRestaurants() {
        return browseRestaurants;
    }

    // EFFECTS: return true if at least one restaurant has this cuisine type in the browse collection
    public boolean containsCuisineBrowseRestaurant(String cuisine) {
        for (Restaurant r : browseRestaurants) {
            if (r.getCuisine().equals(cuisine)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: cuisine to be a cuisine type of at least one restaurant in browse collection
    // EFFECTS: creates a list that only contains restaurants with given cuisine type
    public List<Restaurant> filterByCuisine(String cuisine) {
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant r : browseRestaurants) {
            if (r.getCuisine().equals(cuisine)) {
                filteredRestaurants.add(r);
            }
        }
        return filteredRestaurants;
    }

    // EFFECTS: returns true if there are no restaurants in browse collection
    public boolean hasNothing() {
        return browseRestaurants.isEmpty();
    }

}
