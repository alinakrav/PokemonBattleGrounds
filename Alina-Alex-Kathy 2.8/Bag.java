//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is for making all components of the bag interface. 
 * It creates a Categories object, because there are multiple pages 
 * for it. It has the ability to reach the bag item list, so the method 
 * to change an item's amount is declared here, for decreasing the number 
 * of a specific item. It also removes everything itself, as the last class
 * to leave the world once the user is done with the bag.
 * 
 * @author Alina Kravchenko
 */
public class Bag extends Actor
{
    boolean init = true;
    World world;
    ///////////
    BagCategories categories;

    // the list of all items in bag (get this from world)
    ArrayList<HashMap<String, Integer>> itemList = new ArrayList<HashMap<String, Integer>>();

    public Bag(World curWorld) {//ArrayList<HashMap<String, Integer>> itemList) {
        setImage("Bag.png");
        getImage().scale(800, 600);
        if(curWorld instanceof Battle)
            world = (Battle)curWorld;
        else {
            world = (ScrollingWorld)curWorld;
            setLocation(400,300);
        }
    }

    public void act() 
    {
        prepare(listLocations());
    }   

    // initialises world, then prepares by creating the vategory choice bar of the bag
    public void prepare(int[][] itemLocations) {
        if(init) {
            init = false;
            ///////
            if(world instanceof Battle)
                itemList = ((Battle)world).getBag();
            else
                itemList = ((ScrollingWorld)world).getBag();
            categories = new BagCategories(itemList, itemLocations); // this is where location of category object is chosen
            world.addObject(categories, 0, 0);
        }
    }

    // define and return 2d array of coordinates of all the item locations (taken from ItemsList constructor declaration)
    private int[][] listLocations() {
        //boolean if statement here for whether it's bag or party

        // location of the future ItemsList object (derived from first item location again in BagCategories class)
        int itemX = 510;
        int itemY = 225;

        // list all the possible (maximum) y values in order
        int itemY1 = 61;
        int itemY2 = itemY1 + 50;
        int itemY3 = itemY2 + 50;
        int itemY4 = itemY3 + 50;
        int[][] itemLocations = {{itemX, itemY1}, 
                {itemX, itemY2}, 
                {itemX, itemY3}, 
                {itemX, itemY4}};

        return itemLocations;
    }

    // updates the amount of an item in the bag, deletes the item if amount is less than 1
    public void remapItem(String name, int amount) {
        for(HashMap<String, Integer> map : itemList) {
            for(String key : map.keySet()) {
                if(key.equals(name)) { // find correct item key
                    map.put(key, amount);
                    if(amount < 1) {
                        map.remove(key); // remove the mapping of such item if no more left
                    }
                    amount = -1; // indicator for outer loop to break after this
                    break;
                }
            }
            if(amount == -1) break;
        }
    }

    public void removeEverything() {
        ArrayList<Class> bagClasses = new ArrayList<Class>();
        bagClasses.add(Selection.class); // itemselection checks for objects of other classes in its act(), so should bed first
        bagClasses.add(BagCategories.class);
        bagClasses.add(ItemsList.class);
        bagClasses.add(Item.class);
        bagClasses.add(ItemDescription.class);
        for(Class c: bagClasses)
            world.removeObjects(world.getObjects(c));
        if(world instanceof Battle)
            ((Battle)world).setBag(itemList);
        else
            ((ScrollingWorld)world).setBag(itemList);
        world.removeObject(this);
    }
}
