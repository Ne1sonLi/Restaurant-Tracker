package persistence;

import model.FavouriteRestaurants;
import model.Restaurant;
import model.TryNextRestaurants;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    public void testJsonWriterInvalidFile() {
        try {
            JsonWriter jsonWriter = new JsonWriter("./data\0illegal:fileName.json");
            jsonWriter.open();
            fail("FileNotFoundException was expected");
        } catch (FileNotFoundException e) {
            // expected
        }
    }

    @Test
    public void testWriteTryNextEmpty() {
        try {
            TryNextRestaurants trynext = new TryNextRestaurants();
            assertTrue(trynext.hasNothing());
            assertEquals(0, trynext.getNumRestaurants());

            JsonWriter jsonWriter = new JsonWriter("./data/testWriterEmptyTryNext.json");
            jsonWriter.open();
            jsonWriter.writeTryNext(trynext);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader();
            trynext = jsonReader.readTryNext("./data/testWriterEmptyTryNext.json");
            assertTrue(trynext.hasNothing());
            assertEquals(0, trynext.getNumRestaurants());
        } catch (FileNotFoundException e) {
            fail("Should not have thrown exception");
        } catch (IOException e) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    public void testWriteFavouritesEmpty() {
        try {
            FavouriteRestaurants favourites = new FavouriteRestaurants();
            assertTrue(favourites.hasNothing());
            assertEquals(0, favourites.getNumRestaurants());

            JsonWriter jsonWriter = new JsonWriter("./data/testWriterEmptyFavourites.json");
            jsonWriter.open();
            jsonWriter.writeFavourites(favourites);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader();
            favourites = jsonReader.readFavourites("./data/testWriterEmptyFavourites.json");
            assertTrue(favourites.hasNothing());
            assertEquals(0, favourites.getNumRestaurants());
        } catch (FileNotFoundException e) {
            fail("Should not have thrown exception");
        } catch (IOException e) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    public void testWriteTryNextGeneral() {
        try {
            Restaurant r1 = new Restaurant("Nori", "001 UBC", "Japanese");
            Restaurant r2 = new Restaurant("Pizza Hut", "003 UBC", "Pizza");
            TryNextRestaurants trynext = new TryNextRestaurants();
            trynext.addTryNext(r1);
            trynext.addTryNext(r2);
            assertFalse(trynext.hasNothing());
            assertEquals(2, trynext.getNumRestaurants());

            JsonWriter jsonWriter = new JsonWriter("./data/testWriterGeneralTryNext.json");
            jsonWriter.open();
            jsonWriter.writeTryNext(trynext);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader();
            trynext = jsonReader.readTryNext("./data/testWriterGeneralTryNext.json");
            assertFalse(trynext.hasNothing());
            assertEquals(2, trynext.getNumRestaurants());
            assertEquals(0, trynext.searchTryNext(r1.getName()));
            assertEquals(1, trynext.searchTryNext(r2.getName()));
        } catch (FileNotFoundException e) {
            fail("Should not have thrown exception");
        } catch (IOException e) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    public void testWriterFavouritesGeneral() {
        try{
            Restaurant r1 = new Restaurant("Nori", "001 UBC", "Japanese");
            Restaurant r2 = new Restaurant("Pizza Hut", "003 UBC", "Pizza");
            r1.setRating(8);
            r2.setRating(7);
            FavouriteRestaurants favourites = new FavouriteRestaurants();
            favourites.addFavourite(r1);
            favourites.addFavourite(r2);
            assertFalse(favourites.hasNothing());
            assertEquals(2, favourites.getNumRestaurants());

            JsonWriter jsonWriter = new JsonWriter("./data/testWriterGeneralFavourites.json");
            jsonWriter.open();
            jsonWriter.writeFavourites(favourites);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader();
            favourites = jsonReader.readFavourites("./data/testWriterGeneralFavourites.json");
            assertFalse(favourites.hasNothing());
            assertEquals(2, favourites.getNumRestaurants());
            assertEquals(0, favourites.searchFavourites(r1.getName()));
            assertEquals(1, favourites.searchFavourites(r2.getName()));
            assertEquals(8, favourites.getFavRestaurant(0).getRating());
            assertEquals(7, favourites.getFavRestaurant(1).getRating());
        } catch (FileNotFoundException e) {
            fail("Should not have thrown exception");
        } catch (IOException e) {
            fail("Should not have thrown exception");
        }
    }


}
