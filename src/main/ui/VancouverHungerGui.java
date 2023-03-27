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
    private JPanel mainMenu;
    private JPanel browseMenu;
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
        mainMenu = new JPanel();
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
                //displayTryNextMenu();
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
        browseMenu = new JPanel();
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
        for (Restaurant r : browse.getBrowseRestaurants()) {
            JButton btn = new JButton(r.getName());
            btn.addActionListener(browseRestaurantAL(r.getName(), r.getLocation(), r.getCuisine()));
            buttonList.add(btn);
        }
        JButton returnToMainMenu = new JButton("Return to Main Menu");
        returnToMainMenu.setActionCommand("toMainMenu");
        returnToMainMenu.addActionListener(this);
        buttonList.add(returnToMainMenu);
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

}
