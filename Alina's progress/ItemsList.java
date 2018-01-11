//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

public class ItemsList extends Actor
{
    boolean init = true;
    MyWorld world;
    ///////////
    ArrayList<HashMap<String, Integer>> itemList;
    int x;
    int y;
    
    int y1;
    int y2;
    int y3;
    int y4;
    int[] itemYs;

    ItemSelection selection;
    private String chosen; //string of the item that 'enter' is pressed on

    // the list should be passed to constructor (not made here), and be of type "string" for the item names
    ArrayList<Item> things = new ArrayList<>();
    public ItemsList(ArrayList<HashMap<String, Integer>> itemList, int category, int x, int y) {
        setImage("items" + category + ".png");
        this.x = x;
        this.y = y;

        y1 = y - 60;
        y2 = y1 + 40;
        y3 = y2 + 40;
        y4 = y3 + 40;

        int[] tempYArray = {y1, y2, y3, y4};
        itemYs = tempYArray;

        this.itemList = itemList;

        int tempIndexCounter = 0;
        for(String itemName : itemList.get(category).keySet()) {
            things.add(new Item(itemName, itemList.get(category).get(itemName), x, itemYs[tempIndexCounter++]));
        }
        // always add cancel button at the end
        things.add(new Item("Close_bag", -1, x, itemYs[tempIndexCounter]));
    }

    public void act() 
    {
        prepare();
    }   

    // initialises world, then prepares by creating the vategory choice bar of the bag
    public void prepare() {
        if(init) {
            world = (MyWorld)getWorld();
            init = false;
            ///////

            for(int i = 0; i < 4; i++)
                world.addObject(things.get(i), x, itemYs[i]);

            // track the frame's item based on its location in relation to any item in array of these objects
            selection = new ItemSelection(this);
            world.addObject(selection, x, y1);
        }
    }

    public void select(Item choice) {
        // this is just to test whether selection is tracked or not, delete the next 2 lines later
        //System.out.println(choice);
        //world.removeObject(choice);
        chosen = choice.name;
        world.getObjects(Bag.class).get(0).itemChosen(chosen);
    }

    // removes everything created from this object
    public void removeEverything() {
        world.removeObjects(world.getObjects(Item.class));
        world.removeObject(selection);
    }

    // feeds location of next item to selection frame, and also changes currently selected item variable
    public int getItemLocation(int index) {
        return itemYs[index];
    }

    public int getMaxIndex() {
        return itemYs.length - 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}