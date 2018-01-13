//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

public class ItemsList extends Actor
{
    boolean init = true;
    MyWorld world;
    ///////////
    int category;
    ArrayList<HashMap<String, Integer>> itemList;
    int x;
    int y;

    int y1;
    int y2;
    int y3;
    int y4;
    int[][] itemLocations;

    ItemSelection selection;
    private String chosen; //string of the item that 'enter' is pressed on

    // the list should be passed to constructor (not made here), and be of type "string" for the item names
    ArrayList<Item> things = new ArrayList<>();
    public ItemsList(ArrayList<HashMap<String, Integer>> itemList, int[][] itemLocations, int category, int x, int y) {
        setImage("items" + 0 + ".png"); // set the background of the list image (rectangle)
        this.x = x;
        this.y = y;

        this.itemLocations = itemLocations;
        this.category = category;
        this.itemList = itemList;

        int tempIndexCounter = 0;
        for(String itemName : itemList.get(category).keySet()) {
            things.add(new Item(itemName, itemList.get(category).get(itemName), itemLocations[tempIndexCounter][0], itemLocations[tempIndexCounter++][1]));
        }
        
        // always add cancel button at the end
        things.add(new Item("Close_bag", -1, itemLocations[tempIndexCounter][0], itemLocations[tempIndexCounter][1]));
    }

    public void act() 
    {
        prepare();
    }   

    // initialises world, then prepares by creating the category choice bar of the bag
    public void prepare() {
        if(init) {
            world = (MyWorld)getWorld();
            init = false;
            ///////

            for(int i = 0; i < things.size(); i++) // create objects for all the options
                world.addObject(things.get(i), itemLocations[i][0], itemLocations[i][1]);

            // track the frame's item based on its location in relation to any item in array of these objects
            selection = new ItemSelection(this);
            world.addObject(selection, itemLocations[0][0], itemLocations[0][1]); // create selection around the first item in list
        }
    }

    public void select(Item choice) {
        // this is just to test whether selection is tracked or not, delete the next 2 lines later
        //System.out.println(choice);
        //world.removeObject(choice);
        chosen = choice.name;
        // i think the item should be chosen from this class, because it's less work and a shorter way
        Turns.player.useItem(chosen, false);
        world.getObjects(Bag.class).get(0).removeEverything(); // exit the Bag object back to main screen
        // decided that I'm not using items from the Bag class
        // world.getObjects(Bag.class).get(0).itemChosen(chosen);
    }

    // removes everything created from this object
    public void removeEverything() {
        world.removeObjects(world.getObjects(Item.class));
        world.removeObject(selection);
    }

    // feeds y location of next item to selection frame, and also changes currently selected item variable
    public int[] getItemLocation(int index) {
        return itemLocations[index]; // returns {x, y}
    }

    public int getMaxIndex() {
        return things.size() - 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}