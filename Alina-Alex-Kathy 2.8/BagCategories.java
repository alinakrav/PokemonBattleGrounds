//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

public class BagCategories extends Actor
{
    boolean init = true;
    KeyReader keys;
    Bag bag;
    ////////

    ArrayList<HashMap<String, Integer>> itemsArray;
    int[][] itemLocations;
    int x;
    int y;
    ItemsList itemsList;
    int finalBoxIndex;
    int category = 0;

    public BagCategories(ArrayList<HashMap<String, Integer>> itemsArray, int[][] itemLocations) {
        finalBoxIndex = 7;
        this.itemsArray = itemsArray;
        this.itemLocations = itemLocations;
        setImage("BagCategory" + category + ".png");
    }

    public void act() 
    {
        prepare();
        // change imgaes based on the key pressed --> creates an effect that the player has selected on different bag catergories
        if(keys.keyIs("right") && category < finalBoxIndex) {
            setImage("BagCategory" + ++category + ".png");
            ItemsList tempItemsList = itemsList; // temporarily store old items object in variable to use its location
            itemsList = new ItemsList(itemsArray, itemLocations,category); // create next items object in place of old one
            getWorld().addObject(itemsList, 0, 0); // add new items object to world
            removeItems(tempItemsList); // remove the old items list object
        }
        else if(keys.keyIs("left") && category > 0) {
            setImage("BagCategory" + --category + ".png");
            ItemsList tempItemsList = itemsList;
            itemsList = new ItemsList(itemsArray, itemLocations, category);
            getWorld().addObject(itemsList, 0, 0);
            removeItems(tempItemsList);
        }
    }

    // initialises world variable, then prepares by making the item window in the world
    public void prepare() {
        if(init) {
            init = false;
            if(getWorld() instanceof Battle) {
                keys = ((Battle)getWorld()).getKeys();
            }
            else {
                keys = ((ScrollingWorld)getWorld()).getKeys();
            }
            ///////

            setLocation(400, 300);
            itemsList = new ItemsList(itemsArray, itemLocations, 0); // first category of items array
            getWorld().addObject(itemsList, 0, 0);
        }
    }

    // removes the items list and everything created from that object
    private void removeItems(ItemsList itemsList) {
        itemsList.removeEverything();
        getWorld().removeObject(itemsList);
    }
}
