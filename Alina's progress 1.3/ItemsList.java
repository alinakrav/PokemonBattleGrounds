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
    int[][] locations;

    Selection selection;
    private String chosen; //string of the item that 'enter' is pressed on

    // the list should be passed to constructor (not made here), and be of type "string" for the item names
    ArrayList<Item> things = new ArrayList<>();
    ArrayList<PartyTag> tags = new ArrayList<>();

    public ItemsList(ArrayList<HashMap<String, Integer>> itemList, int[][] locations, int category, int x, int y) {
        setImage("items" + 0 + ".png"); // set the background of the list image (rectangle)
        this.x = x;
        this.y = y;

        this.locations = locations;
        this.category = category;
        this.itemList = itemList;

        int tempIndexCounter = 0;
        for(String itemName : itemList.get(category).keySet()) {
            things.add(new Item(itemName, itemList.get(category).get(itemName), locations[tempIndexCounter][0], locations[tempIndexCounter++][1]));
        }

        // always add cancel button at the end
        things.add(new Item("Close_bag", -1, locations[tempIndexCounter][0], locations[tempIndexCounter][1]));
    }

    public ItemsList(ArrayList<PartyTag> tags, int[][] locations) {
        //setImage("items" + 0 + ".png"); // set the background of the list image (rectangle)
        this.x = locations[0][0]; // try deleting the + 150 later
        this.y = locations[0][1] + 150; // revert back to reference location (first transformed to be y1 from y

        this.locations = locations;
        this.tags = tags;
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
            if(!things.isEmpty()) {
                for(int i = 0; i < things.size(); i++) {// create objects for all the items 
                    world.addObject(things.get(i), things.get(i).getX(), things.get(i).getY());
                }
                // track the frame's item based on its location in relation to any item in array of these objects
                selection = new Selection(things, false, things.get(0));
            }
            else {
                for(int i = 0; i < world.getParty().size(); i++) {// create objects for all the tags based on party pokemon from world
                    tags.add(new PartyTag(world.getParty().get(i), locations[i][0], locations[i][1]));
                    world.addObject(tags.get(i), tags.get(i).getX(), tags.get(i).getY());	
                }
                selection = new Selection(tags, true, tags.get(0));
            }
            // doesn't exist a contructor for pokemonitems yet
            world.addObject(selection, locations[0][0], locations[0][1]); // create selection around the first item in list (x and y coordinates of first array in 2d array are elements 0 and 1
        }
    }

    public void select(Item choice) {
        chosen = choice.name;
        world.getObjects(Bag.class).get(0).remapItem(choice.name, choice.amount - 1); // decrease amount of item chosen
        // i think the item should be chosen from this class, because it's less work and a shorter way
        Turns.player.useItem(chosen, false);
        world.getObjects(Bag.class).get(0).removeEverything(); // exit the Bag object back to main screen
        // decided that I'm not using items from the Bag class
        // world.getObjects(Bag.class).get(0).itemChosen(chosen);
    }

    // removes everything created from this object
    public void removeEverything() {
        world.removeObjects(world.getObjects(Item.class));
        world.removeObject(selection); // remove the selection (last)
    }

    // feeds y location of next item to selection frame, and also changes currently selected item variable
    public int[] getItemLocation(int index) {
        return locations[index]; // returns {x, y}
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