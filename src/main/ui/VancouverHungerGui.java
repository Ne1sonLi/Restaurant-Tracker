package ui;

import model.BrowseRestaurants;
import model.FavouriteRestaurants;
import model.Restaurant;
import model.TryNextRestaurants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Vancouver Hunger graphical user interface application
public class VancouverHungerGui extends JFrame implements ActionListener {

    private BrowseRestaurants browse;
    private List<Restaurant> filtered;
    private TryNextRestaurants trynext;
    private FavouriteRestaurants favourites;
    private JLabel label;

    // EFFECTS: runs the graphical user interface application
    public VancouverHungerGui() {
        super("Vancouver Hunger");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 500));
        browse = new BrowseRestaurants();
        trynext = new TryNextRestaurants();
        favourites = new FavouriteRestaurants();
        initializeRestaurants();
        displayMainMenu();
    }

    // EFFECTS: performs actions involving saving, loading, and returning to previous menus
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            label.setText("Saved Try Next & Favourites Lists!");
        } else if (e.getActionCommand().equals("load")) {
            label.setText("Loaded Try Next & Favourites Lists!");
        } else if (e.getActionCommand().equals("toMainMenu")) {
            displayMainMenu();
        } else if (e.getActionCommand().equals("toBrowseMenu")) {
            displayBrowseMenu();
        } else if (e.getActionCommand().equals("toFilterMenu")) {
            displayFilterMenu();
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
    // EFFECTS: removes the current panel and displays the given panel
    public void displayPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().invalidate();
        getContentPane().add(panel);
        getContentPane().revalidate();
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the main menu page of the application
    public void displayMainMenu() {
        JPanel mainMenu = new JPanel();
        mainMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 15));
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));
        label = new JLabel("Welcome to the Main Menu!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenu.add(label);
        List<JButton> mainMenuButtons = createMainMenuButtons();
        for (JButton btn : mainMenuButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainMenu.add(btn);
        }
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
                //displayFavouritesMenu();
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the browse menu page of the application
    public void displayBrowseMenu() {
        JPanel browseMenu = new JPanel();
        browseMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 15));
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
                displayViewAllRestaurants();
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
//                displayAddToTryNextMenu();
            }
        };
    }

    // EFFECTS: creates action listener for addToFavouritesBtn in main menu and returns it
    public ActionListener addToFavouritesAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                displayAddToFavouritesMenu();
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the browse filter menu page of the application
    public void displayFilterMenu() {
        JPanel filterMenu = new JPanel();
        filterMenu.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 15));
        filterMenu.setLayout(new BoxLayout(filterMenu, BoxLayout.Y_AXIS));
        label = new JLabel("Select a cuisine!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterMenu.add(label);
        List<JButton> cusineButtons = createCuisineButtons();
        for (JButton btn : cusineButtons) {
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
        filteredRestaurants.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 15));
        filteredRestaurants.setLayout(new BoxLayout(filteredRestaurants, BoxLayout.Y_AXIS));
        label = new JLabel("Here are all the " + cuisine + " restaurants!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        filteredRestaurants.add(label);
        List<JButton> browseButtons = createFilteredButtons();
        for (JButton btn : browseButtons) {
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
            btn.addActionListener(browseRestaurantAL(r.getName(), r.getLocation(), r.getCuisine()));
            buttonList.add(btn);
        }
        JButton returnToMainMenu = new JButton("Return to Filter Menu");
        returnToMainMenu.setActionCommand("toFilterMenu");
        returnToMainMenu.addActionListener(this);
        buttonList.add(returnToMainMenu);
        return buttonList;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the browse menu page of the application
    public void displayViewAllRestaurants() {
        JPanel viewAll = new JPanel();
        viewAll.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 15));
        viewAll.setLayout(new BoxLayout(viewAll, BoxLayout.Y_AXIS));
        label = new JLabel("Here are all the restaurants!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewAll.add(label);
        List<JButton> browseButtons = createViewAllButtons();
        for (JButton btn : browseButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            viewAll.add(btn);
        }
        displayPanel(viewAll);
    }

    // EFFECTS: creates a list of JButtons used in the view all menu and returns it
    public List<JButton> createViewAllButtons() {
        List<JButton> buttonList = new ArrayList<>();
        for (Restaurant r : browse.getBrowseRestaurants()) {
            JButton btn = new JButton(r.getName());
            btn.addActionListener(browseRestaurantAL(r.getName(), r.getLocation(), r.getCuisine()));
            buttonList.add(btn);
        }
        JButton returnToBrowseMenu = new JButton("Return to Browse Menu");
        returnToBrowseMenu.setActionCommand("toBrowseMenu");
        returnToBrowseMenu.addActionListener(this);
        buttonList.add(returnToBrowseMenu);
        return buttonList;
    }

    // EFFECTS: creates action listener for a given restaurant in the browse menu and returns it
    public ActionListener browseRestaurantAL(String name, String location, String cuisine) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(name + "\n \t Address : " + location + "\n \t Cusine : " + cuisine);
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the try next menu page of the application
    public void displayTryNextMenu() {

    }


}
