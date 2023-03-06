package persistence;

import model.FavouriteRestaurants;
import model.Restaurant;
import model.TryNextRestaurants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// JsonReader is templated from the Workroom example supplied by CPSC 210. https://github.com/stleary/JSON-java.git
// Represents a reader that reads Try Next and Favourites lists from JSON data stored in file
public class JsonReader {

    // EFFECTS: constructs a JSON reader to read Try Next and Favourites lists from file source
    public JsonReader() {
    }

    // EFFECTS: reads Try Next List from file and returns it;
    // throws IOException if error occurs when reading data from source
    public TryNextRestaurants readTryNext(String fileSource) throws IOException {
        String jsonData = readFile(fileSource);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTryNextRestaurants(jsonObject);
    }

    // EFFECTS: parses TryNextRestaurant from JSON Object and returns it
    private TryNextRestaurants parseTryNextRestaurants(JSONObject jsonObject) {
        TryNextRestaurants trynext = new TryNextRestaurants();
        addAllTryNextRestaurants(trynext, jsonObject);
        return trynext;
    }

    // EFFECTS: reads Favourites List from file and returns it;
    // throws IOException if error occurs when reading data from source
    public FavouriteRestaurants readFavourites(String fileSource) throws IOException {
        String jsonData = readFile(fileSource);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFavouriteRestaurants(jsonObject);
    }

    // EFFECTS: parses FavouriteRestaurants from JSON Object and returns it
    private FavouriteRestaurants parseFavouriteRestaurants(JSONObject jsonObject) {
        FavouriteRestaurants favourites = new FavouriteRestaurants();
        addAllFavouriteRestaurants(favourites, jsonObject);
        return favourites;
    }

    // EFFECTS: reads given source file and returns it as a string
    public String readFile(String fileSource) throws IOException {
        StringBuilder jsonContent = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileSource), StandardCharsets.UTF_8)) {
            stream.forEach(s -> jsonContent.append(s));
        }

        return jsonContent.toString();
    }

    // MODIFIES: trynext
    // EFFECTS: parses restaurants from JSON Object and adds them to trynext
    public void addAllTryNextRestaurants(TryNextRestaurants trynext, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("restaurants");
        for (Object json : jsonArray) {
            JSONObject restaurant = (JSONObject) json;
            addToTryNext(trynext, restaurant);
        }
    }

    // MODIFIES: trynext
    // EFFECTS: parses Restaurant from JSON Object and adds it to trynext
    public void addToTryNext(TryNextRestaurants trynext, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String location = jsonObject.getString("location");
        String cuisine = jsonObject.getString("cuisine");
        Restaurant restaurant = new Restaurant(name, location, cuisine);
        trynext.addTryNext(restaurant);
    }

    // MODIFIES: favourites
    // EFFECTS: parses restaurants from JSON Object and adds them to favourites
    public void addAllFavouriteRestaurants(FavouriteRestaurants favourites, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("restaurants");
        for (Object json : jsonArray) {
            JSONObject restaurant = (JSONObject) json;
            addToFavourites(favourites, restaurant);
        }
    }

    // MODIFIES: favourites
    // EFFECTS: parses Restaurant from JSON Object and adds it to favourites
    private void addToFavourites(FavouriteRestaurants favourites, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String location = jsonObject.getString("location");
        String cuisine = jsonObject.getString("cuisine");
        int rating = jsonObject.getInt("rating");
        Restaurant restaurant = new Restaurant(name, location, cuisine);
        restaurant.setRating(rating);
        favourites.addFavourite(restaurant);
    }

}
