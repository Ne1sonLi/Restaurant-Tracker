package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantTest {

    private Restaurant r1;

    @BeforeEach
    public void setup(){
        r1 = new Restaurant("Open Kitchen", "1111 UBC", "Pizza");
    }

    @Test
    public void testConstructor() {
        assertEquals("Open Kitchen", r1.getName());
        assertEquals("1111 UBC", r1.getLocation());
        assertEquals("Pizza", r1.getCuisine());
        assertEquals(-1, r1.getRating());
    }

    @Test
    public void testSetRatingOnce() {
        assertEquals(-1, r1.getRating());
        r1.setRating(10);
        assertEquals(10, r1.getRating());
    }

    @Test
    public void testSetRatingMultipleTimes() {
        assertEquals(-1, r1.getRating());
        r1.setRating(8);
        assertEquals(8, r1.getRating());
        r1.setRating(3);
        assertEquals(3, r1.getRating());
    }

}
