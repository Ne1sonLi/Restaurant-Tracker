package persistence;

import model.FavouriteRestaurants;
import model.TryNextRestaurants;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// JsonReaderTest is templated from the Workroom example supplied by CPSC 210. https://github.com/stleary/JSON-java.git
public class JsonReaderTest {

    @Test
    void testReadTryNextFileDoesNotExist() {
        try {
            JsonReader jsonReader = new JsonReader();
            jsonReader.readTryNext("./data/fileDoesNotExist.json");
            fail("FileNotFoundException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReadFavouriteFileDoesNotExist() {
        try {
            JsonReader jsonReader = new JsonReader();
            jsonReader.readFavourites("./data/fileGoodLuckFinding.json");
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testReadTryNextEmpty() {
        try {
            JsonReader jsonReader = new JsonReader();
            TryNextRestaurants trynext = jsonReader.readTryNext("./data/testReaderEmptyTryNext.json");

            assertTrue(trynext.hasNothing());
            assertEquals(0, trynext.getNumRestaurants());
        } catch (IOException e) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    public void testReadFavouritesEmpty() {
        try {
            JsonReader jsonReader = new JsonReader();
            FavouriteRestaurants favourites = jsonReader.readFavourites("./data/testReaderEmptyFavourites.json");

            assertTrue(favourites.hasNothing());
            assertEquals(0, favourites.getNumRestaurants());
        } catch (IOException e) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    public void testReadTryNextGeneral() {
        try {
            JsonReader jsonReader = new JsonReader();
            TryNextRestaurants trynext = jsonReader.readTryNext("./data/testReaderGeneralTryNext.json");
            assertFalse(trynext.hasNothing());
            assertEquals(2, trynext.getNumRestaurants());
            assertEquals(0, trynext.searchTryNext("Pizza Hut"));
            assertEquals(1, trynext.searchTryNext("Nori"));
        } catch (IOException e) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    public void testReaderFavouritesGeneral() {
        try {
            JsonReader jsonReader = new JsonReader();
            FavouriteRestaurants favourites = jsonReader.readFavourites("./data/testReaderGeneralFavourites.json");
            assertFalse(favourites.hasNothing());
            assertEquals(2, favourites.getNumRestaurants());
            assertEquals(0, favourites.searchFavourites("Pizza Hut"));
            assertEquals(1, favourites.searchFavourites("Nori"));
        } catch (IOException e) {
            fail("Should not have thrown exception");
        }
    }

}
