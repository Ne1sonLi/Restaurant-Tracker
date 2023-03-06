package persistence;

import model.FavouriteRestaurants;
import model.TryNextRestaurants;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads Try Next and Favourites lists from JSON data stored in file
public class JsonReader {

    private String fileSource;

    // EFFECTS: constructs a JSON reader to read Try Next and Favourites lists from file source
    public JsonReader(String fileSource) {
        this.fileSource = fileSource;
    }

    public TryNextRestaurants readTryNext() throws FileNotFoundException {

        return null;
    }

    public FavouriteRestaurants readFavourites() throws FileNotFoundException {

        return null;
    }

    public String readFile(String fileSource) throws IOException {
        StringBuilder jsonContent = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileSource), StandardCharsets.UTF_8)) {
            stream.forEach(s -> jsonContent.append(s));
        }

        return jsonContent.toString();
    }


}
