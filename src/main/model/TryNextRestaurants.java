package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of restaurants that the user wants to try next
public class TryNextRestaurants {

    private final List<Restaurant> trynext;

    // EFFECTS: constructs a new try next restaurant list that is empty
    public TryNextRestaurants() {
        trynext = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given restaurant to the try next list
    public void addTryNext(Restaurant r) {
        trynext.add(r);
    }

    // EFFECTS: returns the number of restaurants in the try next list
    public int getNumRestaurants() {
        return trynext.size();
    }

    // REQUIRES: 0 <= index < getNumRestaurants()
    // EFFECTS: returns the restaurant in the try next list at the given index
    public Restaurant getRestaurant(int index) {
        return trynext.get(index);
    }

    // EFFECTS: return true is restaurant name is in try next list
    public boolean containsTryNextRestaurant(String name) {
        for (Restaurant r : trynext) {
            if (name.equals(r.getName())) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: restaurant must be in the list
    // EFFECTS: returns the index the restaurant with the given name
    public int searchTryNext(String name) {
        int index = 0;
        for (int i = 0; i < getNumRestaurants(); i++) {
            if (name.equals(trynext.get(i).getName())) {
                index = i;
            }
        }
        return index;
    }

    // EFFECTS: returns true is there are currently no restaurant in the try next list
    public boolean hasNothing() {
        return trynext.isEmpty();
    }

    // REQUIRES: restaurant name must be a restaurant in the try next list
    // MODIFIES: this
    // EFFECTS: removes restaurant with given name from try next list
    public void removeTryNextRestaurant(String name) {
        int index = searchTryNext(name);
        Restaurant r = getRestaurant(index);
        trynext.remove(r);
    }

}
