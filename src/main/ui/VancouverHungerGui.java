package ui;

import model.BrowseRestaurants;
import model.FavouriteRestaurants;
import model.Restaurant;
import model.TryNextRestaurants;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Vancouver Hunger graphical user interface application
public class VancouverHungerGui extends JFrame implements ActionListener {

    private static final String JSON_TRY_NEXT = "./data/TryNextList.json";
    private static final String JSON_FAVOURITES = "./data/Favourites.json";
    private final BrowseRestaurants browse;
    private List<Restaurant> filtered;
    private TryNextRestaurants trynext;
    private FavouriteRestaurants favourites;
    private JLabel label;
    private JTextField field;
    private final JsonWriter jsonWriterTryNext;
    private final JsonWriter jsonWriterFavourites;
    private final JsonReader jsonReader;

    // EFFECTS: runs the graphical user interface application
    public VancouverHungerGui() {
        super("Vancouver Hunger");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(750, 700));
        browse = new BrowseRestaurants();
        trynext = new TryNextRestaurants();
        favourites = new FavouriteRestaurants();
        jsonWriterTryNext = new JsonWriter(JSON_TRY_NEXT);
        jsonWriterFavourites = new JsonWriter(JSON_FAVOURITES);
        jsonReader = new JsonReader();
        initializeRestaurants();
        displayMainMenu();
    }

    // MODIFIES: this
    // EFFECTS: initializes all the restaurants and adds them to the browse collection
    //          creates empty Try Next and Favourites list
    public void initializeRestaurants() {
        Restaurant miku = new Restaurant("Miku", "200 Granville St. #70", "Japanese");
        Restaurant sura = new Restaurant("Sura", "4151 Hazelbridge Way #1830", "Korean");
        Restaurant sushiMura = new Restaurant("Sushi Mura", "5508 Hollybridge Way #115", "Japanese");
        Restaurant deerGarden = new Restaurant("Deer Garden", "8580 Alexandra Rd. #2015", "Chinese");
        Restaurant dolarShop = new Restaurant("Dolar Shop", "5300 No. 3 Rd.", "Hotpot");
        Restaurant haidilao = new Restaurant("Haidilao", "5890 No. 3 Rd. Room 200", "Hotpot");
        Restaurant chungChun = new Restaurant("Chung Chun", "6020 No. 3 Rd.", "Korean");
        Restaurant shanghaiRiver = new Restaurant("Shanghai River", "7831 Westminster Hwy", "Chinese");
        Restaurant chickoChicken = new Restaurant("Chicko Chicken", "4328 No. 3 Rd. #135", "Korean");
        browse.addBrowseRestaurants(miku);
        browse.addBrowseRestaurants(sura);
        browse.addBrowseRestaurants(sushiMura);
        browse.addBrowseRestaurants(deerGarden);
        browse.addBrowseRestaurants(dolarShop);
        browse.addBrowseRestaurants(haidilao);
        browse.addBrowseRestaurants(chungChun);
        browse.addBrowseRestaurants(shanghaiRiver);
        browse.addBrowseRestaurants(chickoChicken);
    }

    // MODIFIES: this
    // EFFECTS: removes the current panel and displays the given panel that is not resizable
    public void displayPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().invalidate();
        getContentPane().add(panel);
        getContentPane().revalidate();
        pack();
        setResizable(false);
        setVisible(true);
    }

    // EFFECTS: performs actions involving saving, loading, and returning to previous menus
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            saveBothRestaurantLists();
            label.setText("Saved Try Next to : " + JSON_TRY_NEXT + " & Favourites to : " + JSON_FAVOURITES);
        } else if (e.getActionCommand().equals("load")) {
            loadBothRestaurantLists();
            label.setText("Loaded Try Next from : " + JSON_TRY_NEXT +  " & Favourites from : " + JSON_FAVOURITES);
        } else if (e.getActionCommand().equals("toMainMenu")) {
            displayMainMenu();
        } else if (e.getActionCommand().equals("toBrowseMenu")) {
            displayBrowseMenu();
        } else if (e.getActionCommand().equals("toFilterMenu")) {
            displayFilterMenu();
        } else if (e.getActionCommand().equals("toTryNextMenu")) {
            displayTryNextMenu();
        } else if (e.getActionCommand().equals("toFavouritesMenu")) {
            displayFavouritesMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the main menu page of the application
    public void displayMainMenu() {
        JPanel mainMenu = new JPanel();
        mainMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));
        label = new JLabel("Welcome to the Main Menu!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenu.add(label);
        List<JButton> mainMenuButtons = createMainMenuButtons();
        for (JButton btn : mainMenuButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainMenu.add(btn);
        }
        JLabel mainMenuImg = new JLabel();
        mainMenuImg.setIcon(new ImageIcon("data/mainMenuImage.jpg"));
        mainMenuImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenu.add(mainMenuImg);
        displayPanel(mainMenu);
    }

    // EFFECTS: creates a list of JButtons used in the main menu and returns it
    public List<JButton> createMainMenuButtons() {
        List<JButton> buttonList = new ArrayList<>();
        JButton browseBtn = new JButton("Browse Restaurants");
        browseBtn.addActionListener(browseBtnAL());
        JButton tryNextBtn = new JButton("Try Next Restaurants");
        tryNextBtn.addActionListener(tryNextBtnAL());
        JButton favouritesBtn = new JButton("Favourite Restaurants");
        favouritesBtn.addActionListener(favouritesBtnAL());
        JButton saveBtn = new JButton("Save Try Next & Favourites List");
        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(this);
        JButton loadBtn = new JButton("Load Try Next & Favourites List");
        loadBtn.setActionCommand("load");
        loadBtn.addActionListener(this);
        buttonList.add(browseBtn);
        buttonList.add(tryNextBtn);
        buttonList.add(favouritesBtn);
        buttonList.add(saveBtn);
        buttonList.add(loadBtn);
        return buttonList;
    }

    // EFFECTS: creates action listener for browseBtn in main menu and returns it
    private ActionListener browseBtnAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBrowseMenu();
            }
        };
    }

    // EFFECTS: creates action listener for tryNextBtn in main menu and returns it
    private ActionListener tryNextBtnAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTryNextMenu();
            }
        };
    }

    // EFFECTS: creates action listener for favouritesBtn in main menu and returns it
    private ActionListener favouritesBtnAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFavouritesMenu();
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the browse menu page of the application
    public void displayBrowseMenu() {
        JPanel browseMenu = new JPanel();
        browseMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        browseMenu.setLayout(new BoxLayout(browseMenu, BoxLayout.Y_AXIS));
        label = new JLabel("Browse the restaurants!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        browseMenu.add(label);
        List<JButton> browseButtons = createBrowseButtons();
        for (JButton btn : browseButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            browseMenu.add(btn);
        }
        displayPanel(browseMenu);
    }

    // EFFECTS: creates a list of JButtons used in the browse menu and returns it
    public List<JButton> createBrowseButtons() {
        List<JButton> buttonList = new ArrayList<>();
        JButton viewAllBtn = new JButton("View All Restaurants");
        viewAllBtn.addActionListener(viewAllAL());
        JButton filterBtn = new JButton("Filter Restaurants");
        filterBtn.addActionListener(filterAL());
        JButton addToTryNextBtn = new JButton("Add to Try Next");
        addToTryNextBtn.addActionListener(addToTryNextAL());
        JButton addToFavouritesBtn = new JButton("Add to Favourites");
        addToFavouritesBtn.addActionListener(addToFavouritesAL());
        JButton returnToMainMenu = new JButton("Return to Main Menu");
        returnToMainMenu.setActionCommand("toMainMenu");
        returnToMainMenu.addActionListener(this);
        buttonList.add(viewAllBtn);
        buttonList.add(filterBtn);
        buttonList.add(addToTryNextBtn);
        buttonList.add(addToFavouritesBtn);
        buttonList.add(returnToMainMenu);
        return buttonList;
    }

    // EFFECTS: creates action listener for viewALLBtn in browse menu and returns it
    public ActionListener viewAllAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBrowseViewAllRestaurants();
            }
        };
    }

    // EFFECTS: creates action listener for filterBtn in browse menu and returns it
    public ActionListener filterAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFilterMenu();
            }
        };
    }

    // EFFECTS: creates action listener for addToTryNextBtn in main menu and returns it
    public ActionListener addToTryNextAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAddToTryNextMenu();
            }
        };
    }

    // EFFECTS: creates action listener for addToFavouritesBtn in main menu and returns it
    public ActionListener addToFavouritesAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAddToFavouritesMenu();
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the browse view all page of the application
    public void displayBrowseViewAllRestaurants() {
        JPanel viewAll = new JPanel();
        viewAll.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        viewAll.setLayout(new BoxLayout(viewAll, BoxLayout.Y_AXIS));
        label = new JLabel("Here are all the restaurants!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewAll.add(label);
        List<JButton> viewAllButtons = createBrowseViewAllButtons();
        for (JButton btn : viewAllButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            viewAll.add(btn);
        }
        displayPanel(viewAll);
    }

    // EFFECTS: creates a list of JButtons used in the view all menu and returns it
    public List<JButton> createBrowseViewAllButtons() {
        List<JButton> buttonList = new ArrayList<>();
        for (Restaurant r : browse.getBrowseRestaurants()) {
            JButton btn = new JButton(r.getName());
            btn.addActionListener(restaurantDetailsAL(r.getName(), r.getLocation(), r.getCuisine()));
            buttonList.add(btn);
        }
        JButton returnToBrowseMenu = new JButton("Return to Browse Menu");
        returnToBrowseMenu.setActionCommand("toBrowseMenu");
        returnToBrowseMenu.addActionListener(this);
        buttonList.add(returnToBrowseMenu);
        return buttonList;
    }

    // EFFECTS: creates action listener for a given restaurant in the browse menu and returns it
    public ActionListener restaurantDetailsAL(String name, String location, String cuisine) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(name + "\n \t Address : " + location + "\n \t Cuisine : " + cuisine);
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the browse filter menu page of the application
    public void displayFilterMenu() {
        JPanel filterMenu = new JPanel();
        filterMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        filterMenu.setLayout(new BoxLayout(filterMenu, BoxLayout.Y_AXIS));
        label = new JLabel("Select a cuisine!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterMenu.add(label);
        List<JButton> cuisineButtons = createCuisineButtons();
        for (JButton btn : cuisineButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            filterMenu.add(btn);
        }
        displayPanel(filterMenu);
    }

    // EFFECTS: creates a list of JButtons used in the browse filter menu and returns it
    public List<JButton> createCuisineButtons() {
        List<JButton> buttonList = new ArrayList<>();
        JButton japaneseBtn = new JButton("Japanese");
        JButton koreanBtn = new JButton("Korean");
        JButton chineseBtn = new JButton("Chinese");
        JButton hotpotBtn = new JButton("Hotpot");
        buttonList.add(japaneseBtn);
        buttonList.add(koreanBtn);
        buttonList.add(chineseBtn);
        buttonList.add(hotpotBtn);
        for (JButton btn : buttonList) {
            btn.addActionListener(cuisineAL(btn.getText()));
        }
        JButton returnToBrowseMenu = new JButton("Return to Browse Menu");
        returnToBrowseMenu.setActionCommand("toBrowseMenu");
        returnToBrowseMenu.addActionListener(this);
        buttonList.add(returnToBrowseMenu);
        return buttonList;
    }

    // EFFECTS: creates action listener for given cuisine in filter menu and returns it
    public ActionListener cuisineAL(String cuisine) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFilteredRestaurants(cuisine);
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the restaurants filtered by a cuisine
    public void displayFilteredRestaurants(String cuisine) {
        filtered = browse.filterByCuisine(cuisine);
        JPanel filteredRestaurants = new JPanel();
        filteredRestaurants.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        filteredRestaurants.setLayout(new BoxLayout(filteredRestaurants, BoxLayout.Y_AXIS));
        label = new JLabel("Here are all the " + cuisine + " restaurants!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        filteredRestaurants.add(label);
        List<JButton> filteredButtons = createFilteredButtons();
        for (JButton btn : filteredButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            filteredRestaurants.add(btn);
        }
        displayPanel(filteredRestaurants);
    }

    // EFFECTS: creates a list of JButtons used in the filtered restaurants menu and returns it
    public List<JButton> createFilteredButtons() {
        List<JButton> buttonList = new ArrayList<>();
        for (Restaurant r : filtered) {
            JButton btn = new JButton(r.getName());
            btn.addActionListener(restaurantDetailsAL(r.getName(), r.getLocation(), r.getCuisine()));
            buttonList.add(btn);
        }
        JButton returnToFilterMenuMenu = new JButton("Return to Filter Menu");
        returnToFilterMenuMenu.setActionCommand("toFilterMenu");
        returnToFilterMenuMenu.addActionListener(this);
        buttonList.add(returnToFilterMenuMenu);
        return buttonList;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the browse add to try next page of the application
    public void displayAddToTryNextMenu() {
        JPanel addToTryNext = new JPanel();
        addToTryNext.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        addToTryNext.setLayout(new BoxLayout(addToTryNext, BoxLayout.Y_AXIS));
        label = new JLabel("Select a restaurant to add to Try Next List");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToTryNext.add(label);
        List<JButton> tryNextButtons = createAddToTryNextButtons();
        for (JButton btn : tryNextButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            addToTryNext.add(btn);
        }
        displayPanel(addToTryNext);
    }

    // EFFECTS: creates a list of JButtons used in the browse add to try next menu and returns it
    public List<JButton> createAddToTryNextButtons() {
        List<JButton> buttonList = new ArrayList<>();
        for (Restaurant r : browse.getBrowseRestaurants()) {
            JButton btn = new JButton(r.getName());
            btn.addActionListener(addToTryNextFromBrowseAL(r.getName()));
            buttonList.add(btn);
        }
        JButton returnToBrowseMenu = new JButton("Return to Browse Menu");
        returnToBrowseMenu.setActionCommand("toBrowseMenu");
        returnToBrowseMenu.addActionListener(this);
        buttonList.add(returnToBrowseMenu);
        return buttonList;
    }

    // MODIFIES: this
    // EFFECTS: creates action listener for restaurant in browse add try next menu and returns it
    //          adds restaurant with given name to try next list if not already in it
    public ActionListener addToTryNextFromBrowseAL(String name) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Restaurant r = browse.getRestaurant(browse.searchBrowseRestaurants(name));
                if (trynext.containsTryNextRestaurant(name)) {
                    label.setText(name + " is already in your Try Next List!");
                } else {
                    trynext.addTryNext(r);
                    label.setText(name + " has been added to Try Next List!");
                }
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the browse add to favourites page of the application
    public void displayAddToFavouritesMenu() {
        JPanel addToFavourites = new JPanel();
        addToFavourites.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        addToFavourites.setLayout(new BoxLayout(addToFavourites, BoxLayout.Y_AXIS));
        label = new JLabel("");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToFavourites.add(label);
        JLabel label2 = new JLabel("Select a restaurant to add to Favourites list");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToFavourites.add(label2);
        List<JButton> favouritesButtons = createAddToFavouritesButtons();
        for (JButton btn : favouritesButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            addToFavourites.add(btn);
        }
        displayPanel(addToFavourites);
    }

    // EFFECTS: creates a list of JButtons used in the browse add to favourites menu and returns it
    public List<JButton> createAddToFavouritesButtons() {
        List<JButton> buttonList = new ArrayList<>();
        for (Restaurant r : browse.getBrowseRestaurants()) {
            JButton btn = new JButton(r.getName());
            btn.addActionListener(addToFavouritesFromBrowseAL(r.getName()));
            buttonList.add(btn);
        }
        JButton returnToBrowseMenu = new JButton("Return to Browse Menu");
        returnToBrowseMenu.setActionCommand("toBrowseMenu");
        returnToBrowseMenu.addActionListener(this);
        buttonList.add(returnToBrowseMenu);
        return buttonList;
    }

    // EFFECTS: creates action listener for restaurant in browse add to favourites menu and returns it
    //          adds restaurant with given name to favourites list if not already in it
    public ActionListener addToFavouritesFromBrowseAL(String name) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (favourites.containsFavouriteRestaurant(name)) {
                    label.setText(name + " is already in your Favourites List!");
                } else {
                    displayAddRatingMenu(name);
                }
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the browse set rating page of the application
    public void displayAddRatingMenu(String name) {
        JPanel addRatingMenu = new JPanel();
        addRatingMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        addRatingMenu.setLayout(new BoxLayout(addRatingMenu, BoxLayout.Y_AXIS));
        label = new JLabel("Please give a rating from 0 - 10 for " + name);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRatingMenu.add(label);
        field = new JTextField();
        field.setMaximumSize(new Dimension(100, 35));
        addRatingMenu.add(field);
        JButton setRatingBtn = new JButton("Set Rating");
        setRatingBtn.addActionListener(setRatingFromBrowseAL(name));
        setRatingBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRatingMenu.add(setRatingBtn);
        displayPanel(addRatingMenu);
    }

    // MODIFIES: this
    // EFFECTS: creates the action listener for set rating button and returns it
    public ActionListener setRatingFromBrowseAL(String name) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rating = Integer.parseInt(field.getText());
                Restaurant r = browse.getRestaurant(browse.searchBrowseRestaurants(name));
                if ((0 <= rating) && (rating <= 10)) {
                    r.setRating(rating);
                    favourites.addFavourite(r);
                    displayAddToFavouritesMenu();
                    label.setText(name + " has been added to Favourites List!");
                } else {
                    displayAddToFavouritesMenu();
                    label.setText("Rating invalid, please try again");
                }
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the try next menu page of the application
    public void displayTryNextMenu() {
        JPanel tryNextMenu = new JPanel();
        tryNextMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        tryNextMenu.setLayout(new BoxLayout(tryNextMenu, BoxLayout.Y_AXIS));
        label = new JLabel("");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        tryNextMenu.add(label);
        JLabel label2 = new JLabel("Try Next Restaurants!");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        tryNextMenu.add(label2);
        List<JButton> tryNextButtons = createTryNextButtons();
        for (JButton btn : tryNextButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            tryNextMenu.add(btn);
        }
        displayPanel(tryNextMenu);
    }

    // EFFECTS: creates a list of JButtons used in the try next menu and returns it
    public List<JButton> createTryNextButtons() {
        List<JButton> buttonList = new ArrayList<>();
        JButton viewAllBtn = new JButton("View all Try Next restaurants");
        viewAllBtn.addActionListener(viewAllTryNextAL());
        JButton moveToFavouritesBtn = new JButton("Move to Favourites List");
        moveToFavouritesBtn.addActionListener(moveToFavouritesAL());
        JButton removeFromTryNextBtn = new JButton("Remove from Try Next List");
        removeFromTryNextBtn.addActionListener(removeFromTryNextAL());
        JButton returnToMainMenu = new JButton("Return to Main Menu");
        returnToMainMenu.setActionCommand("toMainMenu");
        returnToMainMenu.addActionListener(this);
        buttonList.add(viewAllBtn);
        buttonList.add(moveToFavouritesBtn);
        buttonList.add(removeFromTryNextBtn);
        buttonList.add(returnToMainMenu);
        return buttonList;
    }

    // EFFECTS: creates action listener for viewAllBtn in try next menu and returns it
    public ActionListener viewAllTryNextAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (trynext.hasNothing()) {
                    label.setText("Your Try Next list is currently empty!");
                } else {
                    displayTryNextRestaurants();
                }
            }
        };
    }

    // EFFECTS: creates action listener for moveToFavouritesBtn in try next menu and returns it
    public ActionListener moveToFavouritesAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (trynext.hasNothing()) {
                    label.setText("Your Try Next list is currently empty!");
                } else {
                    displayMoveToFavouritesMenu();
                }
            }
        };
    }

    // EFFECTS: creates action listener for removeFromTryNextBtn in try next menu and returns it
    public ActionListener removeFromTryNextAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (trynext.hasNothing()) {
                    label.setText("Your Try Next list is currently empty!");
                } else {
                    displayRemoveFromTryNextMenu();
                }
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the try next view all page of the application
    public void displayTryNextRestaurants() {
        JPanel tryNextViewAll = new JPanel();
        tryNextViewAll.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        tryNextViewAll.setLayout(new BoxLayout(tryNextViewAll, BoxLayout.Y_AXIS));
        label = new JLabel("Here are the restaurants you want to Try Next!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        tryNextViewAll.add(label);
        List<JButton> tryNextViewAllButtons = createTryNextViewAllButtons();
        for (JButton btn : tryNextViewAllButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            tryNextViewAll.add(btn);
        }
        displayPanel(tryNextViewAll);
    }

    // EFFECTS: creates a list of JButtons used in the try next view all menu and returns it
    public List<JButton> createTryNextViewAllButtons() {
        List<JButton> buttonList = new ArrayList<>();
        for (Restaurant r : trynext.getTryNextRestaurants()) {
            JButton btn = new JButton(r.getName());
            btn.addActionListener(restaurantDetailsAL(r.getName(), r.getLocation(), r.getCuisine()));
            buttonList.add(btn);
        }
        JButton returnToBrowseMenu = new JButton("Return to Try Next Menu");
        returnToBrowseMenu.setActionCommand("toTryNextMenu");
        returnToBrowseMenu.addActionListener(this);
        buttonList.add(returnToBrowseMenu);
        return buttonList;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the try next move to favourites page of the application
    public void displayMoveToFavouritesMenu() {
        JPanel moveToFavouritesMenu = new JPanel();
        moveToFavouritesMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        moveToFavouritesMenu.setLayout(new BoxLayout(moveToFavouritesMenu, BoxLayout.Y_AXIS));
        label = new JLabel("Select a restaurant to move to Favourites List");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveToFavouritesMenu.add(label);
        List<JButton> moveToFavouritesButtons = createMoveToFavouritesButtons();
        for (JButton btn : moveToFavouritesButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            moveToFavouritesMenu.add(btn);
        }
        displayPanel(moveToFavouritesMenu);
    }

    // EFFECTS: creates a list of JButtons used in the try next move to favourites menu and returns it
    public List<JButton> createMoveToFavouritesButtons() {
        List<JButton> buttonList = new ArrayList<>();
        for (Restaurant r : trynext.getTryNextRestaurants()) {
            JButton btn = new JButton(r.getName());
            btn.addActionListener(addToFavouritesFromTryNextAL(r.getName()));
            buttonList.add(btn);
        }
        JButton returnToTryNextMenu = new JButton("Return to Try Next Menu");
        returnToTryNextMenu.setActionCommand("toTryNextMenu");
        returnToTryNextMenu.addActionListener(this);
        buttonList.add(returnToTryNextMenu);
        return buttonList;
    }

    // EFFECTS: creates action listener for restaurant in try next move to favourites menu and returns it
    //          adds restaurant with given name to favourites list if not already in it
    public ActionListener addToFavouritesFromTryNextAL(String name) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (favourites.containsFavouriteRestaurant(name)) {
                    label.setText(name + " is already in your Favourites List!");
                } else {
                    displayAddRatingMenuFromTryNext(name);
                }
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the try next set rating page of the application
    public void displayAddRatingMenuFromTryNext(String name) {
        JPanel addRatingMenu = new JPanel();
        addRatingMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        addRatingMenu.setLayout(new BoxLayout(addRatingMenu, BoxLayout.Y_AXIS));
        label = new JLabel("Please give a rating from 0 - 10 for " + name);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRatingMenu.add(label);
        field = new JTextField();
        field.setMaximumSize(new Dimension(100, 35));
        addRatingMenu.add(field);
        JButton setRatingBtn = new JButton("Set Rating");
        setRatingBtn.addActionListener(setRatingFromTryNextAL(name));
        setRatingBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRatingMenu.add(setRatingBtn);
        displayPanel(addRatingMenu);
    }

    // MODIFIES: this
    // EFFECTS: creates the action listener for set rating button from try next menu and returns it
    public ActionListener setRatingFromTryNextAL(String name) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rating = Integer.parseInt(field.getText());
                Restaurant r = browse.getRestaurant(browse.searchBrowseRestaurants(name));
                if ((0 <= rating) && (rating <= 10)) {
                    r.setRating(rating);
                    favourites.addFavourite(r);
                    trynext.removeTryNextRestaurant(name);
                    displayMoveToFavouritesMenu();
                    label.setText(name + " has been added to Favourites List!");
                } else {
                    displayMoveToFavouritesMenu();
                    label.setText("Rating invalid, please try again");
                }
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the try next remove from try next page of the application
    public void displayRemoveFromTryNextMenu() {
        JPanel removeFromTryNextMenu = new JPanel();
        removeFromTryNextMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        removeFromTryNextMenu.setLayout(new BoxLayout(removeFromTryNextMenu, BoxLayout.Y_AXIS));
        label = new JLabel("");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeFromTryNextMenu.add(label);
        JLabel label2 = new JLabel("Select a restaurant to remove from Try Next List");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeFromTryNextMenu.add(label2);
        List<JButton> removeFromTryNextButtons = createRemoveFromTryNextButtons();
        for (JButton btn : removeFromTryNextButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            removeFromTryNextMenu.add(btn);
        }
        displayPanel(removeFromTryNextMenu);
    }

    // EFFECTS: creates list of JButtons used in the try next remove from try next menu and returns it
    public List<JButton> createRemoveFromTryNextButtons() {
        List<JButton> buttonList = new ArrayList<>();
        for (Restaurant r : trynext.getTryNextRestaurants()) {
            JButton btn = new JButton(r.getName());
            btn.addActionListener(removeRestaurantFromTryNextAL(r.getName()));
            buttonList.add(btn);
        }
        JButton returnToTryNextMenu = new JButton("Return to Try Next Menu");
        returnToTryNextMenu.setActionCommand("toTryNextMenu");
        returnToTryNextMenu.addActionListener(this);
        buttonList.add(returnToTryNextMenu);
        return buttonList;
    }

    // MODIFIES: this
    // EFFECTS: creates the action listener for removing restaurant in remove from try next menu and returns it
    public ActionListener removeRestaurantFromTryNextAL(String name) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trynext.removeTryNextRestaurant(name);
                displayRemoveFromTryNextMenu();
                label.setText(name + " has been removed from Try Next List");
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the favourites menu page of the application
    public void displayFavouritesMenu() {
        JPanel favouritesMenu = new JPanel();
        favouritesMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        favouritesMenu.setLayout(new BoxLayout(favouritesMenu, BoxLayout.Y_AXIS));
        label = new JLabel("");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        favouritesMenu.add(label);
        JLabel label2 = new JLabel("Favourite Restaurants!");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        favouritesMenu.add(label2);
        List<JButton> favouritesButtons = createFavouritesButtons();
        for (JButton btn : favouritesButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            favouritesMenu.add(btn);
        }
        displayPanel(favouritesMenu);
    }

    // EFFECTS: creates list of JButtons used in the favourites menu and returns it
    public List<JButton> createFavouritesButtons() {
        List<JButton> buttonList = new ArrayList<>();
        JButton viewAllBtn = new JButton("View all Favourite restaurants");
        viewAllBtn.addActionListener(viewAllFavouritesAL());
        JButton editRatingsBtn = new JButton("Edit Ratings");
        editRatingsBtn.addActionListener(editFavouriteRatingsAL());
        JButton removeFromFavouritesBtn = new JButton("Remove from Favourites List");
        removeFromFavouritesBtn.addActionListener(removeFromFavouritesAL());
        JButton returnToMainMenu = new JButton("Return to Main Menu");
        returnToMainMenu.setActionCommand("toMainMenu");
        returnToMainMenu.addActionListener(this);
        buttonList.add(viewAllBtn);
        buttonList.add(editRatingsBtn);
        buttonList.add(removeFromFavouritesBtn);
        buttonList.add(returnToMainMenu);
        return buttonList;
    }

    // EFFECTS: creates action listener for viewAllBtn in favourites menu and returns it
    public ActionListener viewAllFavouritesAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (favourites.hasNothing()) {
                    label.setText("Your Favourites list is currently empty");
                } else {
                    displayViewAllFavouriteRestaurants();
                }
            }
        };
    }

    // EFFECTS: creates action listener for editRatingsBtn in favourites menu and returns it
    public ActionListener editFavouriteRatingsAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (favourites.hasNothing()) {
                    label.setText("Your Favourites list is currently empty");
                } else {
                    displayEditRatingsMenu();
                }
            }
        };
    }

    // EFFECTS: creates action listener for removeFromFavouritesBtn in favourites menu and returns it
    public ActionListener removeFromFavouritesAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (favourites.hasNothing()) {
                    label.setText("Your Favourites list is currently empty");
                } else {
                    displayRemoveFavouritesMenu();
                }
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the favourites view all menu page of the application
    public void displayViewAllFavouriteRestaurants() {
        JPanel viewAllFavouritesMenu = new JPanel();
        viewAllFavouritesMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        viewAllFavouritesMenu.setLayout(new BoxLayout(viewAllFavouritesMenu, BoxLayout.Y_AXIS));
        label = new JLabel("Here are your Favourite Restaurants!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewAllFavouritesMenu.add(label);
        List<JButton> favouritesViewAllButtons = createViewAllFavouritesButtons();
        for (JButton btn : favouritesViewAllButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            viewAllFavouritesMenu.add(btn);
        }
        displayPanel(viewAllFavouritesMenu);
    }

    // EFFECTS: creates list of JButtons used in the favourites view all menu and returns it
    public List<JButton> createViewAllFavouritesButtons() {
        List<JButton> buttonList = new ArrayList<>();
        for (Restaurant r : favourites.getFavouriteRestaurants()) {
            JButton btn = new JButton(r.getName());
            btn.addActionListener(viewAllFavouriteRestaurantAL(r.getName(), r.getLocation(), r.getCuisine(),
                    r.getRating()));
            buttonList.add(btn);
        }
        JButton returnToBrowseMenu = new JButton("Return to Favourites Menu");
        returnToBrowseMenu.setActionCommand("toFavouritesMenu");
        returnToBrowseMenu.addActionListener(this);
        buttonList.add(returnToBrowseMenu);
        return buttonList;
    }

    // EFFECTS: creates action listener for  in favourites menu and returns it
    public ActionListener viewAllFavouriteRestaurantAL(String name, String location, String cuisine, int rating) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(name + "\n \t Address : " + location + "\n \t Cuisine : " + cuisine
                        + "\n \t Rating : " + rating);
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the favourites edit ratings menu page of the application
    public void displayEditRatingsMenu() {
        JPanel editRatingsMenu = new JPanel();
        editRatingsMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        editRatingsMenu.setLayout(new BoxLayout(editRatingsMenu, BoxLayout.Y_AXIS));
        label = new JLabel("");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        editRatingsMenu.add(label);
        JLabel label2 = new JLabel("Select a restaurant rating to edit");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        editRatingsMenu.add(label2);
        List<JButton> favouritesEditRatingsButtons = createEditFavouritesButtons();
        for (JButton btn : favouritesEditRatingsButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            editRatingsMenu.add(btn);
        }
        displayPanel(editRatingsMenu);
    }

    // EFFECTS: creates list of JButtons used in the favourites edit menu and returns it
    public List<JButton> createEditFavouritesButtons() {
        List<JButton> buttonList = new ArrayList<>();
        for (Restaurant r : favourites.getFavouriteRestaurants()) {
            JButton btn = new JButton(r.getName() + "\t , Rating : " + r.getRating());
            btn.addActionListener(editFavouriteRestaurantsAL(r.getName(), r.getRating()));
            buttonList.add(btn);
        }
        JButton returnToBrowseMenu = new JButton("Return to Favourites Menu");
        returnToBrowseMenu.setActionCommand("toFavouritesMenu");
        returnToBrowseMenu.addActionListener(this);
        buttonList.add(returnToBrowseMenu);
        return buttonList;
    }

    // EFFECTS: creates action listener for buttons in favourites edit menu and returns it
    public ActionListener editFavouriteRestaurantsAL(String name, int rating) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayEditingRatingsMenu(name, rating);
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel when editing restaurant ratings in favourites menu
    public void displayEditingRatingsMenu(String name, int rating) {
        JPanel editRatingMenu = new JPanel();
        editRatingMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        editRatingMenu.setLayout(new BoxLayout(editRatingMenu, BoxLayout.Y_AXIS));
        label = new JLabel("Current rating for " + name + " is : " + rating
                + ". Please enter new rating from 0 - 10");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        editRatingMenu.add(label);
        field = new JTextField();
        field.setMaximumSize(new Dimension(100, 35));
        editRatingMenu.add(field);
        JButton setRatingBtn = new JButton("Set New Rating");
        setRatingBtn.addActionListener(setRatingFromFavouritesAL(name));
        setRatingBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        editRatingMenu.add(setRatingBtn);
        displayPanel(editRatingMenu);
    }

    // MODIFIES: this
    // EFFECTS: creates action listener for setRatingBtn in favourites edit ratings menu
    //          changes the rating of the selected restaurant to the new rating inputted into text field
    public ActionListener setRatingFromFavouritesAL(String name) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rating = Integer.parseInt(field.getText());
                Restaurant r = browse.getRestaurant(browse.searchBrowseRestaurants(name));
                if ((0 <= rating) && (rating <= 10)) {
                    r.setRating(rating);
                    displayEditRatingsMenu();
                    label.setText(name + "'s rating has been changed to " + rating);
                } else {
                    displayEditRatingsMenu();
                    label.setText("Rating invalid, please try again");
                }
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel to remove favourite restaurants in the favourites menu
    public void displayRemoveFavouritesMenu() {
        JPanel removeFromFavouritesMenu = new JPanel();
        removeFromFavouritesMenu.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        removeFromFavouritesMenu.setLayout(new BoxLayout(removeFromFavouritesMenu, BoxLayout.Y_AXIS));
        label = new JLabel("");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeFromFavouritesMenu.add(label);
        JLabel label2 = new JLabel("Select a restaurant to move to Favourites List");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeFromFavouritesMenu.add(label2);
        List<JButton> removeFromFavouritesButtons = createRemoveFromFavouritesButtons();
        for (JButton btn : removeFromFavouritesButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            removeFromFavouritesMenu.add(btn);
        }
        displayPanel(removeFromFavouritesMenu);
    }

    // EFFECTS: creates list of JButtons used in remove from favourites menu in favourites menu
    public List<JButton> createRemoveFromFavouritesButtons() {
        List<JButton> buttonList = new ArrayList<>();
        for (Restaurant r : favourites.getFavouriteRestaurants()) {
            JButton btn = new JButton(r.getName());
            btn.addActionListener(removeRestaurantFromFavouritesAL(r.getName()));
            buttonList.add(btn);
        }
        JButton returnToTryNextMenu = new JButton("Return to Favourites Menu");
        returnToTryNextMenu.setActionCommand("toFavouritesMenu");
        returnToTryNextMenu.addActionListener(this);
        buttonList.add(returnToTryNextMenu);
        return buttonList;
    }

    // MODIFIES: this
    // EFFECTS: creates action listener for removeFromFavouritesBtn and returns it
    public ActionListener removeRestaurantFromFavouritesAL(String name) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = favourites.searchFavourites(name);
                favourites.getFavRestaurant(index).setRating(-1);
                favourites.removeFavourite(index);
                displayRemoveFavouritesMenu();
                label.setText(name + " has been removed from Favourites List");
            }
        };
    }

    // EFFECTS: saves trynext list and favourites list to separate files
    public void saveBothRestaurantLists() {
        try {
            jsonWriterTryNext.open();
            jsonWriterTryNext.writeTryNext(trynext);
            jsonWriterTryNext.close();
            jsonWriterFavourites.open();
            jsonWriterFavourites.writeFavourites(favourites);
            jsonWriterFavourites.close();
            displayMainMenu();
        } catch (FileNotFoundException e) {
            displayMainMenu();
            label.setText("Failed to write file");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads trynext list and favourites list from their respective files
    public void loadBothRestaurantLists() {
        try {
            trynext = jsonReader.readTryNext(JSON_TRY_NEXT);
            favourites = jsonReader.readFavourites(JSON_FAVOURITES);
            displayMainMenu();
        } catch (IOException e) {
            displayMainMenu();
            label.setText("Failed to read file");
        }
    }

}
