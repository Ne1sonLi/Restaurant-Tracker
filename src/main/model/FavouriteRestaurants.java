package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of favourites restaurants added by the user
public class FavouriteRestaurants {

    private final List<Restaurant> favourites;

    // EFFECTS: Constructs a new favourite restaurant list that is empty
    public FavouriteRestaurants() {
        favourites = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds a new favourite restaurant to the favourite restaurants list
    public void addFavourite(Restaurant r) {
        favourites.add(r);
    }

    // EFFECTS: returns the number of favourite restaurants currently in the list
    public int getNumRestaurants() {
        return favourites.size();
    }

    // EFFECTS: returns true if a given restaurant's name is in the favourites list, false otherwise
    public boolean containsFavouriteRestaurant(String name) {
        for (Restaurant r : favourites) {
            if (name.equals(r.getName())) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: name must be the name of a restaurant in the list
    // EFFECTS: returns the index the restaurant with the given name
    public int searchFavourites(String name) {
        int index = 0;
        for (int i = 0; i < getNumRestaurants(); i++) {
            if (name.equals(favourites.get(i).getName())) {
                index = i;
            }
        }
        return index;
    }

    // EFFECTS: returns true if the favourites list is empty, false otherwise
    public boolean hasNothing() {
        return favourites.isEmpty();
    }

    // REQUIRES: 0 <= index < getNumRestaurants()
    // EFFECTS: returns a favourite restaurant in the list at the given index number
    public Restaurant getFavRestaurant(int index) {
        return favourites.get(index);
    }

    // REQUIRES: 0 <= index < getNumRestaurants()
    // MODIFIES: this
    // EFFECTS: removes a favourite restaurant in the list at the given index number
    public void removeFavourite(int index) {
        favourites.remove(index);
    }

}
