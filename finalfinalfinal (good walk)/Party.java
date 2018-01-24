//////////////// PARTY INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class creates the party interface, meaning all 
 * the component the user needs to see the party. locations
 * of PartyTag objects are defined here. The ItemsList is 
 * created here because there is only one that is needed,
 * as there's only one page.
 * 
 * @author Alina Kravchenko
 */
public class Party extends Actor
{
    boolean init = true;
    Battle world;
    ///////////
    ArrayList<PartyTag> tags = new ArrayList<PartyTag>();
    ItemsList partyList;
    // hashmaps of _item name_ and _item quantity_ are in a list (to hold multiple name-quantity pairs), and there are multiple such lists for each category of items

    String itemChosen;

    public Party() {
        ///
        this.setImage("Party.png");
    }

    public void act() 
    {
        prepare(listLocations());
    }   

    // initialises world, then prepares by creating the category choice bar of the bag
    public void prepare(int[][] itemLocations) {
        if(init) {
            world = (Battle)getWorld();
            init = false;
            ///////

            partyList = new ItemsList(tags, itemLocations); // add the object that makes the list of tags
            world.addObject(partyList, 0, 0);
        }
    }

    private int[][] listLocations() {
        // location of the future ItemsList object (derived from first item location again in BagCategories class)
        int itemX = 500;
        int itemY = 300;

        // list all the possible (maximum) y values in order (sideways Z pattern)
        int item1Y, item2Y, item3Y, item4Y, item5Y, item6Y;
        item1Y = 90;
        item2Y = item1Y + 150;
        item3Y = item2Y + 150;
        item4Y = 120;
        item5Y = item4Y + 150;
        item6Y = item5Y + 150;

        int item1X, item2X, item3X, item4X, item5X, item6X;
        item1X = item2X = item3X = 210;
        item4X = item5X = item6X = 600;

        int[][] itemLocations = {{item1X, item1Y}, 
                {item2X, item2Y}, 
                {item3X, item3Y}, 
                {item4X, item4Y},
                {item5X, item5Y},
                {item6X, item6Y}};

        return itemLocations;
    }

    public void itemChosen(String item) {
        itemChosen = item;
        removeEverything();
    }

    // this method is used to check (from the player class) whether an item is chosen (not null), at which point the item name will be deciphered in its own class (maybe same Item class) for properties and used on Pokemon
    public String itemChosen() {
        if(itemChosen == null)  
            return null;
        else {
            removeEverything();
            return itemChosen;
        }
    }

    public void removeEverything() { // except for PartyTag objects
        ArrayList<Class> bagClasses = new ArrayList<Class>();
        bagClasses.add(Selection.class); // itemselection checks for objects of other classes in its act(), so should be removed first
        bagClasses.add(ItemsList.class);
        bagClasses.add(ItemDescription.class);
        for(Class c: bagClasses)
            world.removeObjects(world.getObjects(c));
        world.removeObject(this);
    }
}