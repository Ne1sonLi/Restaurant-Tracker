# Vancouver  Hunger

Vancouver Hunger is a desktop application that will have many restaurants located in the greater Vancouver area. There
will be restaurants for many types of cuisines. From sushi to dessert to tacos, Vancouver Hunger, will surely have a
place that will satiate its user's taste buds. Moreover, it will allow user's to add different restaurants to two
different lists:

- **Favourite**
- **Try-next**

By having these two lists separate from all the restaurants the user can easily locate their favourite restaurants as
well as the ones that they have bookmarked as their next place to visit. In their favourites list, users will be able
to add a **rating** out of 10

Overall, this application will help expose its user's to a bunch of different cuisines near the greater Vancouver area
and allow them to keep track of what restaurants they enjoyed and which ones to try next.

The main user's of this application will be those who either live in the greater Vancouver area and are keen of trying
new places to eat or tourists who are planning to visit. Furthermore, it can also be used by a myriad of UBC students as
not everyone is from the greater Vancouver area. Therefore, they could use it to get some ideas on where to eat during
their next meal out with friends. This application interests me because I enjoy food a lot. When I go hang out with
friends we also enjoy trying out new restaurants but usually don't know which ones to try. Thus, by having an
application like this, we can recommend our favourite restaurants to each other so that we can have the best experience
at that restaurant.

## How to Use

1. Here is the home page where you can view different menus, save your current Try Next & Favourites Lists or load your
   previously saved Try Next & Favourites List
    ![1.png](README_images%2F1.png)
2. Here is the Browse restaurants menu where you can view restaurants, filter, or add to your desired list 
    ![2.png](README_images%2F2.png)
3. This is the view page for all the restaurants currently in the app. You can click on each restaurant to get more information about it
    ![3.png](README_images%2F3.png)
4. This is the view page after we have selected the filter option of `Hotpot`
    ![4.png](README_images%2F4.png)
5. To add restaurants to the Try Next list, simply click on the name of the restaurant and a message will appear once it has been successfully added
    ![5.png](README_images%2F5.png)
6. To add a restaurant to the Favourites list, you will need to provide a integer rating from 0 - 10
    ![6.png](README_images%2F6.png)
7. Here is the Try Next Restaurants menu page. On this page you view your Try Next list, move a restaurnt from your Try Next list to the Favourites list or remove a restaurant from your Try Next list.
    ![7.png](README_images%2F7.png)
8. When viewing your Try Next list, you can also click on the restaurant to get more information about it.
    ![8.png](README_images%2F8.png)
9. Here is the Favourites Restaurants menu page. On this page you can view your Favourites list, edit a restaurants rating, or remove a restaurant from your Favourites list.
    ![9.png](README_images%2F9.png)
10. When viewing your Favourites list, clicking on the restaurant provides information as well as the rating you have given it.
    ![10.png](README_images%2F10.png)
11. When editing a rating, you will see all the current restaurant ratings and once you click on a rating to edit, you will be prompted to input your new integer rating from 0 - 10.
    ![11.png](README_images%2F11.png)

## User Stories

- As a user, I want to be able to browse restaurants filtered by a specific cuisine
- As a user, I want to be able to select a restaurant and add it to my favourites list
- As a user, I want to be able to select a restaurant and add it to my try-next list
- As a user, I want to be able to select a restaurant in my try-next list and remove it
- As a user, I want to be able to select a restaurant in my try-next list and move it to my favourites list
- As a user, I want to be able to select a restaurant in my favourites list and rate it out of 10
- As a user, I want to be able to save my Try Next and Favourites list of restaurants
- As a user, I want to be able to have the option to reload my previously saved Try Next and Favourites lists

## Instructions for Use

- You can add restaurants to the Try Next or Favourites list through the browse menu
- Select the "Browse Restaurants" and click "Add to Try Next" or "Add to Favourites"
- You can also add restaurants to the Favourites list from the Try Next menu
- Select "Try Next Restaurants" and then click "Move to Favourites List"
- You can remove restaurants in your Try Next and Favourites list
- To remove restaurants from your Try Next list, select "Try Next Restaurants" and then click "Remove from Try Next
  List"
- To remove restaurants from your Favourites list, select "Favourite Restaurants" and then click "Remove from Favourites
  List"
- You can also filter restaurants by their cuisine in the browse menu
- Select "Browse Restaurants" and click "Filter Restaurants" then select your desired cuisine
- You can locate my visual component on the main menu when you first open the application
- You can save your Try Next and Favourites list by clicking "Save Try Next & Favourites List" on the main menu
- You can load your saved Try Next and Favourites list by clicking "Load Try Next & Favourites List" on the main menu

## Phase 4: Task 2

Added to Try Next : Miku, Sun Apr 09 17:25:13 PDT 2023

Rating set for Sushi Mura : 10, Sun Apr 09 17:25:19 PDT 2023

Added to Favourites : Sushi Mura, Sun Apr 09 17:25:19 PDT 2023

Removed from Try Next : Miku, Sun Apr 09 17:25:27 PDT 2023

Rating set for Sushi Mura : 9, Sun Apr 09 17:25:33 PDT 2023

Removed from Favourites : Sushi Mura, Sun Apr 09 17:25:39 PDT 2023

## Further Changes

The first change that I would make would be to decrease the coupling in my model package. Currently, the
BrowseRestaurants, TryNextRestaurants, and FavouriteRestaurants classes contain lots of duplicated code. Moreover, they
also essentially perform the same task which is maintaining a specific list of Restaurants and performing the same
functions for that list. Therefore, to reduce the amount of duplicated code, I would create a class called
RestaurantList
and have those three classes extend RestaurantList. In RestaurantList I would have all the methods that are shared among
those three classes such as adding a restaurant, removing a restaurant, checking if a certain restaurant is in the list,
getting the index of a certain restaurant, etc. Then when calling those methods in their respective classes, I can just
replace the body of the method with a call to its superclass's method.

Another change I would make would be in the VancouverHungerGui class. For that class, when creating a new JPanel, I
essentially copied and pasted the lines of code that set the border and layout of the panel. However, this resulted in
me having to copy and paste it out each time I created a new JPanel which was quite often. Therefore, to reduce the
coupling there, I would create another method in the VancouverHungerGui class that creates a new JPanel with sets
its border and layout. Then I would replace all those duplicated fragments of code with a call to that method. This
would be extremely useful if I ever decide to change the layout or border widths in the future as I can just change
that one method instead of changing each individual call. A disadvantage of this would be that I would have to have a
consistent layout and border for all my panels as they will be created from the same method. This may limit the variety
I can have in my program gui.
