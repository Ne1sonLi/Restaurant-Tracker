package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TryNextRestaurantsTest {

    private TryNextRestaurants trynext;
    private Restaurant r1;
    private Restaurant r2;
    private Restaurant r3;

    @BeforeEach
    public void setup() {
        trynext = new TryNextRestaurants();
        r1 = new Restaurant("Nori", "001 UBC", "Japanese");
        r2 = new Restaurant("Pacific Poke", "002 UBC", "Japanese");
        r3 = new Restaurant( "Pizza Hut", "003 UBC", "Pizza");
    }

    @Test
    public void testConstructor() {
        assertTrue(trynext.hasNothing());
        assertEquals(0, trynext.getNumRestaurants());
    }

    @Test
    public void testGetRetaurants() {
        trynext.addTryNext(r1);
        trynext.addTryNext(r2);
        trynext.addTryNext(r3);
        assertFalse(trynext.getTryNextRestaurants().isEmpty());
        assertEquals(3, trynext.getTryNextRestaurants().size());
        assertEquals(r1, trynext.getTryNextRestaurants().get(0));
        assertEquals(r2, trynext.getTryNextRestaurants().get(1));
        assertEquals(r3, trynext.getTryNextRestaurants().get(2));
    }

    @Test
    public void testAddTryNextRestaurantsOnce() {
        trynext.addTryNext(r1);
        assertFalse(trynext.hasNothing());
        assertEquals(1, trynext.getNumRestaurants());
        assertTrue(trynext.containsTryNextRestaurant(r1.getName()));
    }

    @Test
    public void testAddTryNextRestaurantsMultipleTimes() {
        trynext.addTryNext(r1);
        assertFalse(trynext.hasNothing());
        assertEquals(1, trynext.getNumRestaurants());
        assertTrue(trynext.containsTryNextRestaurant(r1.getName()));
        trynext.addTryNext(r3);
        assertEquals(2, trynext.getNumRestaurants());
        assertTrue(trynext.containsTryNextRestaurant(r1.getName()));
        assertTrue(trynext.containsTryNextRestaurant(r3.getName()));
        trynext.addTryNext(r2);
        assertEquals(3, trynext.getNumRestaurants());
        assertTrue(trynext.containsTryNextRestaurant(r1.getName()));
        assertTrue(trynext.containsTryNextRestaurant(r2.getName()));
        assertTrue(trynext.containsTryNextRestaurant(r3.getName()));
    }

    @Test
    public void testAddTryNextRestaurantSameRestaurant() {
        trynext.addTryNext(r1);
        assertEquals(1, trynext.getNumRestaurants());
        assertTrue(trynext.containsTryNextRestaurant(r1.getName()));
        trynext.addTryNext(r1);
        assertEquals(1, trynext.getNumRestaurants());
        assertTrue(trynext.containsTryNextRestaurant(r1.getName()));
    }


    @Test
    public void testContainsTryNextRestaurantsInTryNext() {
        assertFalse(trynext.containsTryNextRestaurant("Nori"));
        trynext.addTryNext(r1);
        assertTrue(trynext.containsTryNextRestaurant("Nori"));
    }

    @Test
    public void testSearchTryNextRestaurantOneElementInList() {
        trynext.addTryNext(r2);
        assertEquals(0, trynext.searchTryNext(r2.getName()));
        assertEquals(r2, trynext.getRestaurant(trynext.searchTryNext(r2.getName())));
    }

    @Test
    public void testSearchTryNextRestaurantsMultipleElementsInList() {
        trynext.addTryNext(r1);
        trynext.addTryNext(r2);
        trynext.addTryNext(r3);
        assertEquals(2, trynext.searchTryNext(r3.getName()));
        assertEquals(1, trynext.searchTryNext(r2.getName()));
        assertEquals(r2, trynext.getRestaurant(trynext.searchTryNext(r2.getName())));
        assertEquals(r3, trynext.getRestaurant(trynext.searchTryNext(r3.getName())));
    }

    @Test
    public void testRemoveTryNextOneElementInList() {
        trynext.addTryNext(r1);
        assertEquals(1, trynext.getNumRestaurants());
        assertTrue(trynext.containsTryNextRestaurant(r1.getName()));
        trynext.removeTryNextRestaurant(r1.getName());
        assertEquals(0, trynext.getNumRestaurants());
        assertTrue(trynext.hasNothing());
        assertFalse(trynext.containsTryNextRestaurant(r1.getName()));
    }

    @Test
    public void testRemoveTryNextMultipleElements() {
        trynext.addTryNext(r1);
        trynext.addTryNext(r2);
        trynext.addTryNext(r3);
        assertEquals(3, trynext.getNumRestaurants());
        assertTrue(trynext.containsTryNextRestaurant(r1.getName()));
        assertTrue(trynext.containsTryNextRestaurant(r2.getName()));
        assertTrue(trynext.containsTryNextRestaurant(r3.getName()));
        trynext.removeTryNextRestaurant(r1.getName());
        assertEquals(2, trynext.getNumRestaurants());
        assertFalse(trynext.containsTryNextRestaurant(r1.getName()));
        assertTrue(trynext.containsTryNextRestaurant(r2.getName()));
        assertTrue(trynext.containsTryNextRestaurant(r3.getName()));
        trynext.removeTryNextRestaurant(r2.getName());
        assertEquals(1, trynext.getNumRestaurants());
        assertFalse(trynext.containsTryNextRestaurant(r1.getName()));
        assertFalse(trynext.containsTryNextRestaurant(r2.getName()));
        assertTrue(trynext.containsTryNextRestaurant(r3.getName()));
        trynext.removeTryNextRestaurant(r3.getName());
        assertEquals(0, trynext.getNumRestaurants());
        assertFalse(trynext.containsTryNextRestaurant(r1.getName()));
        assertFalse(trynext.containsTryNextRestaurant(r2.getName()));
        assertFalse(trynext.containsTryNextRestaurant(r3.getName()));
    }
}
