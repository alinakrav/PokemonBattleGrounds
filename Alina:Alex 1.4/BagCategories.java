//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

public class BagCategories extends Actor
{
    boolean init = true;
    KeyReader keys;
    MyWorld world;
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
                itemsList = new ItemsList(itemsArray, itemLocations,category, tempItemsList.getX(), tempItemsList.getY()); // create next items object in place of old one
                world.addObject(itemsList, itemsList.getX(), itemsList.getY()); // add new items object to world
                removeItems(tempItemsList); // remove the old items list object
            }
            else if(keys.keyIs("left") && category > 0) {
                setImage("bagCategories" + --category + ".png");
                ItemsList tempItemsList = itemsList;
                itemsList = new ItemsList(itemsArray, itemLocations, category, tempItemsList.getX(), tempItemsList.getY());
                world.addObject(itemsList, itemsList.getX(), itemsList.getY());
                removeItems(tempItemsList);
            }
        }  
    }

    // initialises world variable, then prepares by making the item window in the world
    public void prepare() {
        if(init) {
            world = (MyWorld)getWorld();
            keys = world.getKeys();
            init = false;
            ///////

            int itemsListX = itemLocations[0][0];
            int itemsListY = itemLocations[0][1] + 60; // revert back to reference location (first transformed to be y1 from y
            itemsList = new ItemsList(itemsArray, itemLocations, 0, itemsListX, itemsListY); // first category of items array
            world.addObject(itemsList, itemsList.getX(), itemsList.getY());
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
