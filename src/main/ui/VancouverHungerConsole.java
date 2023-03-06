package ui;

import model.*;

import java.util.List;
import java.util.Scanner;

// Vancouver Hunger console based application
public class VancouverHungerConsole {

    private BrowseRestaurants browse;
    private List<Restaurant> filtered;
    private TryNextRestaurants trynext;
    private FavouriteRestaurants favourites;

    private Scanner input;

    // EFFECTS: runs the console based ui application
    public VancouverHungerConsole() {
        runConsoleUI();
    }

    // MODIFIES: this
    // EFFECTS: perform operations according to user input
    public void runConsoleUI() {
        boolean continueRunning = true;

        initializeRestaurants();
        displayMainMenu();

        while (continueRunning) {

            String operation = input.nextLine();

            if (operation.equals("x")) {
                continueRunning = false;
            } else {
                processMainMenuOperation(operation);
            }
        }
        System.out.println("\n Enjoy your meal!");
    }

    // EFFECTS: displays the main menu of options of operations for the user
    private void displayMainMenu() {
        System.out.println("\n Main Menu:");
        System.out.println("\t b -> Browse restaurants");
        System.out.println("\t t -> Try Next list");
        System.out.println("\t f -> Favourites list");
        System.out.println("\t x -> Quit Vancouver Hunger");
    }

    // EFFECTS: processes the operation on the main menu
    public void processMainMenuOperation(String operation) {
        if (operation.equals("b")) {
            displayBrowseMenu();
        } else if (operation.equals("t")) {
            displayTryNextMenu();
        } else if (operation.equals("f")) {
            displayFavouritesMenu();
        } else {
            System.out.println("Invalid selection, please try again.");
            displayMainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes all the restaurants and adds them to the browse collection
    //          creates empty Try Next and Favourites list
    public void initializeRestaurants() {
        Restaurant miku = new Restaurant("Miku", "200 Granville St. #70", "Japanese");
        Restaurant sura = new Restaurant("Sura", "4151 Hazelbridge Way #1830", "Korean");
        Restaurant sushiMura = new Restaurant("Sushi Mura", "5508 Hollybridge Way #115", "Japanese");
        Restaurant deerGarden = new Restaurant("Deer Garden", "8580 Alexandra Rd. #2015", "Chinese");
        Restaurant mcDonalds = new Restaurant("McDonald's", "5728 University Blvd #101", "Burger");
        Restaurant uncleFatih = new Restaurant("Uncle Fatih's", "6045 University Blvd", "Pizza");
        Restaurant dlChicken = new Restaurant("DownLow Chicken", "6065 University Blvd", "Chicken");
        browse = new BrowseRestaurants();
        trynext = new TryNextRestaurants();
        favourites = new FavouriteRestaurants();
        browse.addBrowseRestaurants(miku);
        browse.addBrowseRestaurants(sura);
        browse.addBrowseRestaurants(sushiMura);
        browse.addBrowseRestaurants(deerGarden);
        browse.addBrowseRestaurants(mcDonalds);
        browse.addBrowseRestaurants(uncleFatih);
        browse.addBrowseRestaurants(dlChicken);
        input = new Scanner(System.in);
    }

    // EFFECTS: displays the browse collection menu options for the user
    public void displayBrowseMenu() {
        System.out.println("\n Browse Collection Menu:");
        System.out.println("\t v -> View all restaurants");
        System.out.println("\t c -> Filter by cuisine");
        System.out.println("\t t -> Add restaurant to Try Next");
        System.out.println("\t f -> Add restaurant to Favourites");
        System.out.println("\t q -> Go back to Main Menu");
        String operation = input.nextLine();
        processBrowseMenuOperation(operation);
    }

    // EFFECTS: Processes the operation in the browse menu
    public void processBrowseMenuOperation(String operation) {
        if (operation.equals("v")) {
            displayBrowseRestaurants();
        } else if (operation.equals("c")) {
            displayBrowseRestaurantsFilterMenu();
        } else if (operation.equals("t")) {
            displayBrowseTryNextMenu();
        } else if (operation.equals("f")) {
            displayBrowseFavouritesMenu();
        } else if (operation.equals("q")) {
            displayMainMenu();
        } else {
            System.out.println("Invalid selection, please try again.");
            displayBrowseMenu();
        }
    }

    // EFFECTS: displays all restaurants in the browse collection as a numbered list
    public void displayRestaurants() {
        System.out.println("\n Here are all the restaurants:");
        for (int i = 0; i < browse.getNumRestaurants(); i++) {
            int index = i + 1;
            System.out.println("\t" + index + ". " + browse.getRestaurant(i).getName());
        }
    }

    // EFFECTS: displays all restaurants in browse collection and input options for the user
    public void displayBrowseRestaurants() {
        displayRestaurants();
        System.out.println("For more details, select a restaurant");
        System.out.println("q -> Go back to Browse Menu");
        String operation = input.nextLine();
        processBrowseMenuViewAllOperation(operation);
    }

    // EFFECTS: processes the user input in the browse view all menu
    public void processBrowseMenuViewAllOperation(String operation) {
        if (browse.containsBrowseRestaurant(operation)) {
            showRestaurantDetails(operation);
            displayBrowseRestaurants();
        } else if (operation.equals("q")) {
            displayBrowseMenu();
        } else {
            System.out.println("Invalid selection, please try again.");
            displayBrowseRestaurants();
        }
    }

    // EFFECTS: displays the restaurant name, address (location), and cuisine type
    public void showRestaurantDetails(String name) {
        Restaurant r = browse.getRestaurant(browse.searchBrowseRestaurants(name));
        System.out.println("\n " + r.getName());
        System.out.println("\t Address: " + r.getLocation());
        System.out.println("\t Cuisine: " + r.getCuisine());
    }

    // EFFECTS: displays all the different cuisine types in browse collection along with user input options
    public void displayBrowseRestaurantsFilterMenu() {
        System.out.println("\n Cuisines types:");
        System.out.println("\t 1. Japanese");
        System.out.println("\t 2. Korean");
        System.out.println("\t 3. Chinese");
        System.out.println("\t 4. Burger");
        System.out.println("\t 5. Pizza");
        System.out.println("\t 6. Chicken");
        System.out.println("To filter, select a type of cuisine");
        System.out.println("q -> Go back to Browse Menu");
        String operation = input.nextLine();
        processBrowseRestaurantsFilterOperation(operation);
    }

    // EFFECTS: processes the user input from the browse filter menu
    public void processBrowseRestaurantsFilterOperation(String operation) {
        if (browse.containsCuisineBrowseRestaurant(operation)) {
            showFilteredRestaurants(operation);
        } else if (operation.equals("q")) {
            displayBrowseMenu();
        } else {
            System.out.println("Invalid selection, please try again.");
            displayBrowseRestaurantsFilterMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays all restaurant from browse collection with given cuisine type along with user input options
    public void showFilteredRestaurants(String cuisine) {
        System.out.println("\n " + cuisine + " restaurants:");
        filtered = browse.filterByCuisine(cuisine);
        for (Restaurant r : filtered) {
            int index = filtered.indexOf(r) + 1;
            System.out.println("\t " + index + ". " + r.getName());
        }
        System.out.println("For more details, select a restaurant");
        System.out.println("q -> Go back to Filter Menu");
        String operation = input.nextLine();
        processFilteredRestaurantsOperation(operation, cuisine);
    }

    // EFFECTS: processes the user input from the filtered restaurants menu
    public void processFilteredRestaurantsOperation(String operation, String cuisine) {
        if (filteredRestaurantsContains(operation)) {
            showRestaurantDetails(operation);
            showFilteredRestaurants(cuisine);
        } else if (operation.equals("q")) {
            displayBrowseRestaurantsFilterMenu();
        } else {
            System.out.println("Invalid selection, please try again.");
            showFilteredRestaurants(cuisine);
        }
    }

    // EFFECTS: returns true if given restaurant name is in the filtered restaurants list
    public boolean filteredRestaurantsContains(String name) {
        for (Restaurant r : filtered) {
            if (name.equals(r.getName())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: displays all browse restaurant along with try next menu options
    public void displayBrowseTryNextMenu() {
        displayRestaurants();
        System.out.println("Select a restaurant to add to Try Next list");
        System.out.println("q -> Go back to Browse Menu");
        String operation = input.nextLine();
        processBrowseTryNextMenuOperation(operation);
    }

    // EFFECTS: processes user input from the browse try next menu
    public void processBrowseTryNextMenuOperation(String operation) {
        if (trynext.containsTryNextRestaurant(operation)) {
            System.out.println("\n " + operation + " is already in your Try Next list!");
            displayBrowseTryNextMenu();
        } else if (browse.containsBrowseRestaurant(operation)) {
            addToTryNext(operation);
        } else if (operation.equals("q")) {
            displayBrowseMenu();
        } else {
            System.out.println("Invalid selection, please try again.");
            displayBrowseTryNextMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds selected restaurant to Try Next list and returns user to browse try next menu
    public void addToTryNext(String name) {
        int index = browse.searchBrowseRestaurants(name);
        trynext.addTryNext(browse.getRestaurant(index));
        System.out.println("\n " + name + " has been added to Try Next list!");
        displayBrowseTryNextMenu();
    }

    // EFFECTS: displays all browse restaurants along with favourites menu options
    public void displayBrowseFavouritesMenu() {
        displayRestaurants();
        System.out.println("Select a restaurant to add to Favourites list");
        System.out.println("q -> Go back to Browse Menu");
        String operation = input.nextLine();
        processBrowseFavouritesMenuOperation(operation);
    }

    // EFFECTS: processes user input from browse favourites menu
    public void processBrowseFavouritesMenuOperation(String operation) {
        if (favourites.containsFavouriteRestaurant(operation)) {
            System.out.println("\n " + operation + " is already in your Favourites list!");
            displayBrowseFavouritesMenu();
        } else if (browse.containsBrowseRestaurant(operation)) {
            addToFavouritesMenu(operation);
        } else if (operation.equals("q")) {
            displayBrowseMenu();
        } else {
            System.out.println("Invalid selection, please try again.");
            displayBrowseFavouritesMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds given restaurant name to Favourites list along with rating of 1 - 10 from user
    public void addToFavouritesMenu(String name) {
        int index = browse.searchBrowseRestaurants(name);
        Restaurant restaurant = browse.getRestaurant(index);
        System.out.println("Please rate " + name + " from 1 to 10:");
        int rating = Integer.parseInt(input.nextLine());

        if ((0 <= rating) && (rating <= 10)) {
            restaurant.setRating(rating);
            favourites.addFavourite(restaurant);
            System.out.println("\n " + restaurant.getName() + " has been added to Favourites list!");
            displayBrowseFavouritesMenu();
        } else {
            System.out.println("Rating is invalid, please try again");
            addToFavouritesMenu(restaurant.getName());
        }
    }

    // EFFECTS: displays the try next restaurant menu options for the user
    public void displayTryNextMenu() {
        System.out.println("\n Try Next Menu:");
        System.out.println("\t v -> View all Try Next restaurants");
        System.out.println("\t m -> Move restaurant to Favourites");
        System.out.println("\t r -> Remove restaurant from Try Next");
        System.out.println("\t q -> Go back to Main Menu");
        String operation = input.nextLine();
        processTryNextMenuOperation(operation);
    }

    // EFFECTS: processes user input from the try next menu
    public void processTryNextMenuOperation(String operation) {
        if (operation.equals("v")) {
            displayTryNextViewAll();
        } else if (operation.equals("m")) {
            displayMoveTryNextRestaurantMenu();
        } else if (operation.equals("r")) {
            displayRemoveTryNextRestaurantMenu();
        } else if (operation.equals("q")) {
            displayMainMenu();
        } else {
            System.out.println("Invalid selection, please try again");
            displayTryNextMenu();
        }
    }

    // EFFECTS: displays all restaurants in the try next list as a numbered list
    public void displayTryNextRestaurants() {
        if (trynext.hasNothing()) {
            System.out.println("\n Your Try Next list is currently empty");
            System.out.println("Add some restaurants from the Browse menu");
        } else {
            System.out.println("\n Here are the restaurants you want to Try Next:");
            for (int i = 0; i < trynext.getNumRestaurants(); i++) {
                int index = i + 1;
                System.out.println("\t" + index + ". " + trynext.getRestaurant(i).getName());
            }
        }
    }

    // EFFECTS: displays all try next restaurants along with user input options
    public void displayTryNextViewAll() {
        displayTryNextRestaurants();
        if (!trynext.hasNothing()) {
            System.out.println("\n");
            System.out.println("For more details type the name of the restaurant");
            System.out.println("q -> Go back to Try Next Menu");
            String operation = input.nextLine();
            processTryNextMenuViewAllOperation(operation);
        } else {
            displayTryNextMenu();
        }
    }

    // EFFECTS: processes user input from the try next menu
    public void processTryNextMenuViewAllOperation(String operation) {
        if (trynext.containsTryNextRestaurant(operation)) {
            showRestaurantDetails(operation);
            displayTryNextViewAll();
        } else if (operation.equals("q")) {
            displayTryNextMenu();
        } else {
            System.out.println("Selection invalid, please try again");
            displayTryNextViewAll();
        }
    }

    // EFFECTS: displays all try next restaurants and try next move restaurant menu options for user
    public void displayMoveTryNextRestaurantMenu() {
        displayTryNextRestaurants();
        if (!trynext.hasNothing()) {
            System.out.println("Select a restaurant to add to the Favourites list");
            System.out.println("q -> Go back to Try Next Menu");
            String operation = input.nextLine();
            processMoveTryNextRestaurantOperation(operation);
        } else {
            displayTryNextMenu();
        }
    }

    // EFFECTS: processes user input from move try next restaurant  menu
    public void processMoveTryNextRestaurantOperation(String operation) {
        if (favourites.containsFavouriteRestaurant(operation)) {
            System.out.println("\n " + operation + " is already in your Favourites list!");
            displayMoveTryNextRestaurantMenu();
        } else if (trynext.containsTryNextRestaurant(operation)) {
            moveToFavourites(operation);
        } else if (operation.equals("q")) {
            displayTryNextMenu();
        } else {
            System.out.println("Invalid selection, please try again");
            displayMoveTryNextRestaurantMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds restaurant to Favourites list with rating of 1 - 10 and removes from Try Next list
    public void moveToFavourites(String name) {
        int index = trynext.searchTryNext(name);
        Restaurant restaurant = trynext.getRestaurant(index);
        System.out.println("Please rate " + name + " from 1 to 10:");
        int rating = Integer.parseInt(input.nextLine());

        if ((0 <= rating) && (rating <= 10)) {
            restaurant.setRating(rating);
            favourites.addFavourite(restaurant);
            System.out.println("\n " + restaurant.getName() + " has been moved to Favourites list!");
            trynext.removeTryNextRestaurant(name);
            displayTryNextMenu();
        } else {
            System.out.println("Rating is invalid, please try again");
            moveToFavourites(name);
        }
    }

    // EFFECTS: displays remove try next menu options for user and all restaurants in try next list
    public void displayRemoveTryNextRestaurantMenu() {
        displayTryNextRestaurants();
        if (!trynext.hasNothing()) {
            System.out.println("Select a restaurant to remove from the Try Next list");
            System.out.println("q -> Go back to Try Next Menu");
            String operation = input.nextLine();
            processRemoveTryNextRestaurantOperation(operation);
        } else {
            displayTryNextMenu();
        }
    }

    // EFFECTS: processes user input from the remove try next menu
    public void processRemoveTryNextRestaurantOperation(String operation) {
        if (trynext.containsTryNextRestaurant(operation)) {
            trynext.removeTryNextRestaurant(operation);
            System.out.println(operation + " has been removed from Try Next list");
            displayRemoveTryNextRestaurantMenu();
        } else if (operation.equals("q")) {
            displayTryNextMenu();
        } else {
            System.out.println("Invalid selection, please try again");
            displayRemoveTryNextRestaurantMenu();
        }
    }

    // EFFECTS: displays the favourite restaurant menu options for the user
    public void displayFavouritesMenu() {
        System.out.println("\n Favourites Menu:");
        System.out.println("\t v -> View all Favourite restaurants");
        System.out.println("\t e -> Edit ratings");
        System.out.println("\t r -> Remove restaurant from Favourites");
        System.out.println("\t q -> Go back to Main Menu");
        String operation = input.nextLine();
        processFavouritesMenu(operation);

    }

    // EFFECTS: processes user input from the favourite restaurant menu
    public void processFavouritesMenu(String operation) {
        if (operation.equals("v")) {
            displayAllFavourites();
        } else if (operation.equals("e")) {
            displayEditFavouritesMenu();
        } else if (operation.equals("r")) {
            displayRemoveFavouritesMenu();
        } else if (operation.equals("q")) {
            displayMainMenu();
        } else {
            System.out.println("Invalid selection, please try again");
            displayFavouritesMenu();
        }
    }

    // EFFECTS: displays all restaurant currently in the favourites list
    public void displayFavouriteRestaurants() {
        if (favourites.hasNothing()) {
            System.out.println("\n Your Favourites list is currently empty");
            System.out.println("Add some restaurants from the Browse menu");
        } else {
            System.out.println("\n Here are your Favourite restaurants:");
            for (int i = 0; i < favourites.getNumRestaurants(); i++) {
                int index = i + 1;
                System.out.println("\t" + index + ". " + favourites.getFavRestaurant(i).getName()
                        + " , rating : " + favourites.getFavRestaurant(i).getRating());
            }
        }
    }

    // EFFECTS: displays all restaurants in favourites list along with user input options
    public void displayAllFavourites() {
        displayFavouriteRestaurants();
        if (!favourites.hasNothing()) {
            System.out.println("For more details type the name of the restaurant");
            System.out.println("q -> Go back to Favourites Menu");
            String operation = input.nextLine();
            processDisplayAllFavouritesOperation(operation);
        } else {
            displayFavouritesMenu();
        }
    }

    // EFFECTS: processes user input options from favourites view all menu
    public void processDisplayAllFavouritesOperation(String operation) {
        if (favourites.containsFavouriteRestaurant(operation)) {
            showRestaurantDetails(operation);
            displayAllFavourites();
        } else if (operation.equals("q")) {
            displayFavouritesMenu();
        } else {
            System.out.println("Invalid selection, please try again");
            displayAllFavourites();
        }
    }

    // EFFECTS: displays all restaurants in favourites list along with user input options to edit list
    public void displayEditFavouritesMenu() {
        displayFavouriteRestaurants();
        if (!favourites.hasNothing()) {
            System.out.println("Select the Favourite restaurant's rating you would like to edit");
            System.out.println("q -> Go back to Favourites Menu");
            String operation = input.nextLine();
            processDisplayEditFavouritesOperation(operation);
        } else {
            displayFavouritesMenu();
        }
    }

    // EFFECTS: processes user input from favourites edit menu
    public void processDisplayEditFavouritesOperation(String operation) {
        if (favourites.containsFavouriteRestaurant(operation)) {
            editFavouriteRating(operation);
        } else if (operation.equals("q")) {
            displayFavouritesMenu();
        } else {
            System.out.println("Invalid selection, please try again");
            displayEditFavouritesMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the current rating of the selected restaurant to the new rating given by user
    public void editFavouriteRating(String name) {
        int index = favourites.searchFavourites(name);
        Restaurant restaurant = favourites.getFavRestaurant(index);
        System.out.println("The current rating for " + name + " is " + restaurant.getRating());
        System.out.println("Please enter your new rating for " + name + " :");
        int rating = Integer.parseInt(input.nextLine());


        if ((rating >= 0) && (rating <= 10)) {
            restaurant.setRating(rating);
            System.out.println("The rating for " + name + " is now " + rating);
            displayEditFavouritesMenu();
        } else {
            System.out.println("Invalid rating, please try again");
            editFavouriteRating(name);
        }
    }

    // EFFECTS: displays all restaurants in favourites list and user options to remove a restaurant
    public void displayRemoveFavouritesMenu() {
        displayFavouriteRestaurants();
        if (!favourites.hasNothing()) {
            System.out.println("Select the Favourite restaurant's you would like to remove");
            System.out.println("q -> Go back to Favourites Menu");
            String operation = input.nextLine();
            processDisplayRemoveFavouritesOperation(operation);
        } else {
            displayFavouritesMenu();
        }
    }

    // EFFECTS: processes user input to remove a restaurant from favourites list
    public void processDisplayRemoveFavouritesOperation(String operation) {
        if (favourites.containsFavouriteRestaurant(operation)) {
            removeFromFavourites(operation);
        } else if (operation.equals("q")) {
            displayFavouritesMenu();
        } else {
            System.out.println("Invalid selection, please try again");
            displayRemoveFavouritesMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes selected restaurant from favourites list and sets the rating to -1 (default rating)
    public void removeFromFavourites(String name) {
        int index = favourites.searchFavourites(name);
        favourites.getFavRestaurant(index).setRating(-1);
        favourites.removeFavourite(index);
        System.out.println(name + " has been removed from your Favourites list");
        displayRemoveFavouritesMenu();
    }
}