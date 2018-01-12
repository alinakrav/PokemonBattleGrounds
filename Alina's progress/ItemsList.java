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
    int[][] itemYs;

    ItemSelection selection;
    private String chosen; //string of the item that 'enter' is pressed on

    // the list should be passed to constructor (not made here), and be of type "string" for the item names
    ArrayList<Item> things = new ArrayList<>();
    public ItemsList(ArrayList<HashMap<String, Integer>> itemList, int category, int x, int y) {
        setImage("items" + 1 + ".png"); // set the background of the list image (rectangle)
        this.x = x;
        this.y = y;

        // list all the possible (maximum) y values in order
        y1 = y - 60;
        y2 = y1 + 40;
        y3 = y2 + 40;
        y4 = y3 + 40;

        int[][] tempYArray = {{x, y1}, {x, y2}, {x, y3}, {x, y4}};
        itemYs = tempYArray;
        tempYArray = null;

        this.category = category;
        this.itemList = itemList;

        int tempIndexCounter = 0;
        for(String itemName : itemList.get(category).keySet()) {
            things.add(new Item(itemName, itemList.get(category).get(itemName), itemYs[tempIndexCounter][0], itemYs[tempIndexCounter++][1]));
        }
        // always add cancel button at the end
        things.add(new Item("Close_bag", -1, itemYs[tempIndexCounter][0], itemYs[tempIndexCounter][1]));
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
                world.addObject(things.get(i), itemYs[i][0], itemYs[i][1]);

            // track the frame's item based on its location in relation to any item in array of these objects
            selection = new ItemSelection(this);
            world.addObject(selection, itemYs[0][0], itemYs[0][1]); // create selection around the first item in list
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
    public int getItemLocation(int index) {
        return itemYs[index][1];
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