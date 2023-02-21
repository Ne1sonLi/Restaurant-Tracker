package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BrowseRestaurantsTest {

    private BrowseRestaurants browse;
    private Restaurant r1;
    private Restaurant r2;
    private Restaurant r3;

    @BeforeEach
    public void setup() {
        browse = new BrowseRestaurants();
        r1 = new Restaurant("Nori", "001 UBC", "Japanese");
        r2 = new Restaurant("Pacific Poke", "002 UBC", "Japanese");
        r3 = new Restaurant( "Pizza Hut", "003 UBC", "Pizza");
    }

    @Test
    public void testConstructor() {
        assertTrue(browse.hasNothing());
        assertEquals(0, browse.getNumRestaurants());
    }

    @Test
    public void testAddBrowseRestaurantsOnce() {
        browse.addBrowseRestaurants(r1);
        assertFalse(browse.hasNothing());
        assertEquals(1, browse.getNumRestaurants());
        assertTrue(browse.containsBrowseRestaurant(r1.getName()));
    }

    @Test
    public void testAddBrowseRestaurantsMultipleTimes() {
        browse.addBrowseRestaurants(r1);
        assertFalse(browse.hasNothing());
        assertEquals(1, browse.getNumRestaurants());
        assertTrue(browse.containsBrowseRestaurant(r1.getName()));
        browse.addBrowseRestaurants(r3);
        assertEquals(2, browse.getNumRestaurants());
        assertTrue(browse.containsBrowseRestaurant(r1.getName()));
        assertTrue(browse.containsBrowseRestaurant(r3.getName()));
        browse.addBrowseRestaurants(r2);
        assertEquals(3, browse.getNumRestaurants());
        assertTrue(browse.containsBrowseRestaurant(r1.getName()));
        assertTrue(browse.containsBrowseRestaurant(r2.getName()));
        assertTrue(browse.containsBrowseRestaurant(r3.getName()));
    }

    @Test
    public void testAddBrowseRestaurantsAddSameRestaurant() {
        browse.addBrowseRestaurants(r1);
        assertEquals(1, browse.getNumRestaurants());
        assertTrue(browse.containsBrowseRestaurant(r1.getName()));
        browse.addBrowseRestaurants(r1);
        assertEquals(1, browse.getNumRestaurants());
        assertTrue(browse.containsBrowseRestaurant(r1.getName()));
    }

    @Test
    public void testContainsBrowseRestaurantNoInBrowse() {
        assertFalse(browse.containsBrowseRestaurant("Panda Express"));
        browse.addBrowseRestaurants(r1);
        assertFalse(browse.containsBrowseRestaurant("Panda Express"));
        assertFalse(browse.containsBrowseRestaurant("Norii"));
        assertFalse(browse.containsBrowseRestaurant("Nor"));
    }

    @Test
    public void testContainsBrowseRestaurantsInBrowse() {
        assertFalse(browse.containsBrowseRestaurant("Nori"));
        browse.addBrowseRestaurants(r1);
        assertTrue(browse.containsBrowseRestaurant("Nori"));
    }

    @Test
    public void testSearchBrowseRestaurantOneElementInList() {
        browse.addBrowseRestaurants(r2);
        assertEquals(0, browse.searchBrowseRestaurants(r2.getName()));
        assertEquals(r2, browse.getRestaurant(browse.searchBrowseRestaurants(r2.getName())));
    }

    @Test
    public void testSearchBrowseRestaurantsMultipleElementsInList() {
        browse.addBrowseRestaurants(r1);
        browse.addBrowseRestaurants(r2);
        browse.addBrowseRestaurants(r3);
        assertEquals(2, browse.searchBrowseRestaurants(r3.getName()));
        assertEquals(1, browse.searchBrowseRestaurants(r2.getName()));
        assertEquals(r2, browse.getRestaurant(browse.searchBrowseRestaurants(r2.getName())));
        assertEquals(r3, browse.getRestaurant(browse.searchBrowseRestaurants(r3.getName())));
    }

    @Test
    public void testContainsCuisineNotInBrowse() {
        assertFalse(browse.containsCuisineBrowseRestaurant("Pizza"));
        assertFalse(browse.containsCuisineBrowseRestaurant("Japanese"));
        browse.addBrowseRestaurants(r1);
        assertFalse(browse.containsCuisineBrowseRestaurant("Pizza"));
        assertFalse(browse.containsCuisineBrowseRestaurant("Japan"));
        assertFalse(browse.containsCuisineBrowseRestaurant("Japanes"));
    }

    @Test
    public void testContainsCuisineInBrowse() {
        assertFalse(browse.containsCuisineBrowseRestaurant("Pizza"));
        assertFalse(browse.containsCuisineBrowseRestaurant("Japanese"));
        browse.addBrowseRestaurants(r1);
        browse.addBrowseRestaurants(r2);
        browse.addBrowseRestaurants(r3);
        assertTrue(browse.containsCuisineBrowseRestaurant("Pizza"));
        assertTrue(browse.containsCuisineBrowseRestaurant("Japanese"));
    }

    @Test
    public void testFilteredRestaurantOneRestaurantInBrowse() {
        browse.addBrowseRestaurants(r1);
        List<Restaurant> filtered = browse.filterByCuisine(r1.getCuisine());
        assertEquals(1, filtered.size());
        assertTrue(filtered.contains(r1));
    }

    @Test
    public void testFilterOneRestaurantFromMultipleInBrowse() {
        browse.addBrowseRestaurants(r1);
        browse.addBrowseRestaurants(r2);
        browse.addBrowseRestaurants(r3);
        List<Restaurant> filtered = browse.filterByCuisine("Pizza");
        assertEquals(1, filtered.size());
        assertTrue(filtered.contains(r3));
    }

    @Test
    public void testFilterMultipleRestaurantsFromMultipleInBrowse() {
        browse.addBrowseRestaurants(r1);
        browse.addBrowseRestaurants(r2);
        browse.addBrowseRestaurants(r3);
        List<Restaurant> filtered = browse.filterByCuisine("Japanese");
        assertEquals(2, filtered.size());
        assertTrue(filtered.contains(r1));
        assertTrue(filtered.contains(r2));
    }

}
