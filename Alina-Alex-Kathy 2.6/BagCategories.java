//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

public class BagCategories extends Actor
{
    boolean init = true;
    KeyReader keys;
    World world;
    ////////

    ArrayList<HashMap<String, Integer>> itemsArray;
    int[][] itemLocations;
    int x;
    int y;

    ItemsList itemsList;

    int category = 0;
    int finalBoxIndex = 2;

    public BagCategories(ArrayList<HashMap<String, Integer>> itemsArray, int[][] itemLocations, int x, int y) {
        setImage("bagCategories0.png");
        this.x = x;
        this.y = y;
        this.itemsArray = itemsArray;
        this.itemLocations = itemLocations;
    }

    public void act() 
    {
        prepare();

        if(keys.keyNotNull()) {
            if(keys.keyIs("right") && category < finalBoxIndex) {
                setImage("bagCategories" + ++category + ".png");
                ItemsList tempItemsList = itemsList; // temporarily store old items object in variable to use its location
                itemsList = new ItemsList(itemsArray, itemLocations,category); // create next items object in place of old one
                world.addObject(itemsList, 0, 0); // add new items object to world
                removeItems(tempItemsList); // remove the old items list object
            }
            else if(keys.keyIs("left") && category > 0) {
                setImage("bagCategories" + --category + ".png");
                ItemsList tempItemsList = itemsList;
                itemsList = new ItemsList(itemsArray, itemLocations, category);
                world.addObject(itemsList, 0, 0);
                removeItems(tempItemsList);
            }
        }  
    }

    // initialises world variable, then prepares by making the item window in the world
    public void prepare() {
        if(init) {
            if(getWorld() instanceof Battle)
                world = (Battle)getWorld();
            else 
                world = (ScrollingWorld)getWorld();
            keys = world.getKeys();
            setLocation(100, 200);
            init = false;
            ///////

            itemsList = new ItemsList(itemsArray, itemLocations, 0); // first category of items array
            world.addObject(itemsList, 0, 0);
        }
    }

    // removes the items list and everything created from that object
    private void removeItems(ItemsList itemsList) {
        itemsList.removeEverything();
        world.removeObject(itemsList);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
