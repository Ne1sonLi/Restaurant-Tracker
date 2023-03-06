package persistence;

import model.FavouriteRestaurants;
import model.TryNextRestaurants;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representations of restaurant lists to file
public class JsonWriter {

    private PrintWriter writer;
    private String fileDestination;

    // EFFECTS: constructs a JSON writer to write JSON Objects to the given destination file
    public JsonWriter(String destination) {
        this.fileDestination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer destination, if destination cannot be opened, throw FileNotFoundException
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileDestination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Try Next restaurants into file destination
    public void writeTryNext(TryNextRestaurants trynext) {
        JSONObject jsonObject = trynext.toJson();
        saveToFile(jsonObject.toString(6));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Favourites restaurants into file destination
    public void writeFavourites(FavouriteRestaurants favourites) {
        JSONObject jsonObject = favourites.toJson();
        saveToFile(jsonObject.toString(6));
    }

    // MODIFIES: this
    // EFFECTS: write string to file
    public void saveToFile(String jsonObject) {
        writer.print(jsonObject);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

}
