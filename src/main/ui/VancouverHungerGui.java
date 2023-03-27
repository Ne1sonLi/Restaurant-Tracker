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
    private JPanel panel2;
    private JLabel label;

    public VancouverHungerGui() {
        super("Vancouver Hunger");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(300, 300));
        initializeRestaurants();
        displayMainMenu();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            label.setText("Saved Try Next & Favourites Lists!");
        } else if (e.getActionCommand().equals("load")) {
            label.setText("Loaded Try Next & Favourites Lists!");
        }

    }

    // MODIFIES: this
    // EFFECTS: initializes all the restaurants and adds them to the browse collection
    //          creates empty Try Next and Favourites list
    public void initializeRestaurants() {
        browse = new BrowseRestaurants();
        trynext = new TryNextRestaurants();
        favourites = new FavouriteRestaurants();
        Restaurant miku = new Restaurant("Miku", "200 Granville St. #70", "Japanese");
        Restaurant sura = new Restaurant("Sura", "4151 Hazelbridge Way #1830", "Korean");
        Restaurant sushiMura = new Restaurant("Sushi Mura", "5508 Hollybridge Way #115", "Japanese");
        Restaurant deerGarden = new Restaurant("Deer Garden", "8580 Alexandra Rd. #2015", "Chinese");
        Restaurant dolarShop = new Restaurant("Dolar Shop", "5300 No. 3 Rd", "Hotpot");
        Restaurant haidilao = new Restaurant("Haidilao", "5890 No. 3 Rd Room 200", "Hotpot");
        Restaurant chungChun = new Restaurant("Chung Chun", "6020 No. 3 R", "Korean");
        Restaurant shanghaiRiver = new Restaurant("Shanghai River", "7831 Westminster Hwy", "Chinese");
        Restaurant chickoChicken = new Restaurant("Chicko Chicken", "4328 No. 3 Rd #135", "Korean");
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
        getContentPane().removeAll();
//        getContentPane().invalidate();
        getContentPane().add(mainMenu);
//        getContentPane().revalidate();
        pack();
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: creates a list of JButtons used in the main menu
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
                //displayBrowseMenu();
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

}
