//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

public class ItemsList extends Actor
{
    boolean init = true;
    World world;
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
    ArrayList<Item> items = new ArrayList<Item>(); // items in the bag
    ArrayList<PartyTag> tags = new ArrayList<PartyTag>();

    public ItemsList(ArrayList<HashMap<String, Integer>> itemList, int[][] locations, int category) {
        this.locations = locations;
        this.category = category;
        this.itemList = itemList;

        int tempIndexCounter = 0;
        for(String itemName : itemList.get(category).keySet()) {
            items.add(new Item(itemName, itemList.get(category).get(itemName), locations[tempIndexCounter][0], locations[tempIndexCounter++][1]));
        }

        // always add cancel button at the end
        items.add(new Item("Close", -1, locations[tempIndexCounter][0], locations[tempIndexCounter][1]));
    }

    public ItemsList(ArrayList<PartyTag> tags, int[][] locations) {
        this.locations = locations;
        this.tags = tags;
    }

    public void act() 
    {
        if(getWorld() instanceof Battle)
            prepareInBattle();
        else
            prepareInScrolling();
    }   

    // initialises world, then prepares by creating the category choice bar of the bag
    public void prepareInBattle() {
        if(init) { 
            world = (Battle)getWorld();
            setLocation(515, 225);
            init = false;
            ///////

            if(!items.isEmpty()) { // if the list is of bag items
                for(int i = 0; i < items.size(); i++) {// create objects for all the items 
                    getWorld().addObject(items.get(i), items.get(i).getX(), items.get(i).getY());
                }
                // track the frame's item based on its location in relation to any item in array of these objects
                selection = new Selection(items, false, items.get(0), true);
            }
            else { // if list is not for bag
                getWorld().removeObject(((Battle)getWorld()).player);
                for(int i = 0; i < ((Battle)getWorld()).getParty().size(); i++) {// create objects for all the tags based on party pokemon from world
                    tags.add(new PartyTag(((Battle)getWorld()).getParty().get(i), locations[i][0], locations[i][1]));
                    getWorld().addObject(tags.get(i), tags.get(i).getX(), tags.get(i).getY());	
                }
                selection = new Selection(tags, true, tags.get(0));
            }
            getWorld().addObject(selection, locations[0][0], locations[0][1]); // create selection around the first item in list (x and y coordinates of first array in 2d array are elements 0 and 1
        }
    }

    //duplicate of prepareInBattle, but with class set to ScrollingWorld (and no selection pressing)
    public void prepareInScrolling() {
        if(init) { 
            world = (ScrollingWorld)getWorld();
            setLocation(515, 225);
            init = false;
            ///////
            if(!items.isEmpty()) { // if list is for bag
                for(int i = 0; i < items.size(); i++) {// create objects for all the items 
                    getWorld().addObject(items.get(i), items.get(i).getX(), items.get(i).getY());
                }
                // track the frame's item based on its location in relation to any item in array of these objects
                selection = new Selection(items, false, items.get(0), false);
            }
            else { // if list is not for bag (for tags)
                getWorld().removeObject(((ScrollingWorld)getWorld()).player);
                for(int i = 0; i < ((ScrollingWorld)getWorld()).getParty().size(); i++) {// create objects for all the tags based on party pokemon from world
                    tags.add(new PartyTag(((ScrollingWorld)getWorld()).getParty().get(i), locations[i][0], locations[i][1]));
                    getWorld().addObject(tags.get(i), tags.get(i).getX(), tags.get(i).getY());	
                }
                selection = new Selection(tags, true, tags.get(0));
            }
            getWorld().addObject(selection, locations[0][0], locations[0][1]); // create selection around the first item in list (x and y coordinates of first array in 2d array are elements 0 and 1
        }
    }

    // removes everything created from this object
    public void removeEverything() {
        getWorld().removeObjects(getWorld().getObjects(Item.class));
        getWorld().removeObject(selection); // remove the selection (last)
    }

    // feeds y location of next item to selection frame, and also changes currently selected item variable
    public int[] getItemLocation(int index) {
        return locations[index]; // returns {x, y}
    }

    public int getMaxIndex() {
        return items.size() - 1;
    }
}