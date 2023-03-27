package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FavouriteRestaurantsTest {

    private FavouriteRestaurants favourites;
    private Restaurant r1;
    private Restaurant r2;
    private Restaurant r3;

    @BeforeEach
    public void setup() {
        favourites = new FavouriteRestaurants();
        r1 = new Restaurant("Nori", "001 UBC", "Japanese");
        r2 = new Restaurant("Pacific Poke", "002 UBC", "Japanese");
        r3 = new Restaurant("Pizza Hut", "003 UBC", "Pizza");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, favourites.getNumRestaurants());
        assertTrue(favourites.hasNothing());
    }

    @Test
    public void testGetRestaurants() {
        favourites.addFavourite(r1);
        favourites.addFavourite(r2);
        favourites.addFavourite(r3);
        assertFalse(favourites.getFavouriteRestaurants().isEmpty());
        assertEquals(3, favourites.getFavouriteRestaurants().size());
        assertEquals(r1, favourites.getFavouriteRestaurants().get(0));
        assertEquals(r2, favourites.getFavouriteRestaurants().get(1));
        assertEquals(r3, favourites.getFavouriteRestaurants().get(2));
    }

    @Test
    public void testAddFavouriteOneRestaurant() {
        favourites.addFavourite(r1);
        assertEquals(1, favourites.getNumRestaurants());
        assertTrue(favourites.containsFavouriteRestaurant(r1.getName()));
    }

    @Test
    public void testAddFavouriteMultipleRestaurants() {
        favourites.addFavourite(r1);
        assertFalse(favourites.hasNothing());
        assertEquals(1, favourites.getNumRestaurants());
        assertTrue(favourites.containsFavouriteRestaurant(r1.getName()));
        favourites.addFavourite(r3);
        assertEquals(2, favourites.getNumRestaurants());
        assertTrue(favourites.containsFavouriteRestaurant(r1.getName()));
        assertTrue(favourites.containsFavouriteRestaurant(r3.getName()));
        favourites.addFavourite(r2);
        assertEquals(3, favourites.getNumRestaurants());
        assertTrue(favourites.containsFavouriteRestaurant(r1.getName()));
        assertTrue(favourites.containsFavouriteRestaurant(r2.getName()));
        assertTrue(favourites.containsFavouriteRestaurant(r3.getName()));
    }

    @Test
    public void testAddFavouriteRestaurantAlreadyInList() {
        favourites.addFavourite(r1);
        assertEquals(1, favourites.getNumRestaurants());
        assertTrue(favourites.containsFavouriteRestaurant(r1.getName()));
        favourites.addFavourite(r1);
        assertEquals(1, favourites.getNumRestaurants());
        assertTrue(favourites.containsFavouriteRestaurant(r1.getName()));
    }

    @Test
    public void testSearchFavouritesOneElementInList() {
        favourites.addFavourite(r2);
        assertEquals(0, favourites.searchFavourites(r2.getName()));
        assertEquals(r2, favourites.getFavRestaurant(favourites.searchFavourites(r2.getName())));
    }

    @Test
    public void testSearchTryNextRestaurantsMultipleElementsInList() {
        favourites.addFavourite(r1);
        favourites.addFavourite(r2);
        favourites.addFavourite(r3);
        assertEquals(2, favourites.searchFavourites(r3.getName()));
        assertEquals(1, favourites.searchFavourites(r2.getName()));
        assertEquals(r2, favourites.getFavRestaurant(favourites.searchFavourites(r2.getName())));
        assertEquals(r3, favourites.getFavRestaurant(favourites.searchFavourites(r3.getName())));
    }

    @Test
    public void testRemoveTryNextOneElementInList() {
        favourites.addFavourite(r1);
        assertEquals(1, favourites.getNumRestaurants());
        assertTrue(favourites.containsFavouriteRestaurant(r1.getName()));
        favourites.removeFavourite(favourites.searchFavourites(r1.getName()));
        assertEquals(0, favourites.getNumRestaurants());
        assertTrue(favourites.hasNothing());
        assertFalse(favourites.containsFavouriteRestaurant(r1.getName()));
    }

    @Test
    public void testRemoveTryNextMultipleElements() {
        favourites.addFavourite(r1);
        favourites.addFavourite(r2);
        favourites.addFavourite(r3);
        assertEquals(3, favourites.getNumRestaurants());
        assertTrue(favourites.containsFavouriteRestaurant(r1.getName()));
        assertTrue(favourites.containsFavouriteRestaurant(r2.getName()));
        assertTrue(favourites.containsFavouriteRestaurant(r3.getName()));
        favourites.removeFavourite(favourites.searchFavourites(r1.getName()));
        assertEquals(2, favourites.getNumRestaurants());
        assertFalse(favourites.containsFavouriteRestaurant(r1.getName()));
        assertTrue(favourites.containsFavouriteRestaurant(r2.getName()));
        assertTrue(favourites.containsFavouriteRestaurant(r3.getName()));
        favourites.removeFavourite(favourites.searchFavourites(r2.getName()));
        assertEquals(1, favourites.getNumRestaurants());
        assertFalse(favourites.containsFavouriteRestaurant(r1.getName()));
        assertFalse(favourites.containsFavouriteRestaurant(r2.getName()));
        assertTrue(favourites.containsFavouriteRestaurant(r3.getName()));
        favourites.removeFavourite(favourites.searchFavourites(r3.getName()));
        assertEquals(0, favourites.getNumRestaurants());
        assertFalse(favourites.containsFavouriteRestaurant(r1.getName()));
        assertFalse(favourites.containsFavouriteRestaurant(r2.getName()));
        assertFalse(favourites.containsFavouriteRestaurant(r3.getName()));
    }

}
