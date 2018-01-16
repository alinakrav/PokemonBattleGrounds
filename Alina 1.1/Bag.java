//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */
public class Bag extends Actor
{
    boolean init = true;
    MyWorld world;
    ///////////
    BagCategories categories;

    // hashmaps of _item name_ and _item quantity_ are in a list (to hold multiple name-quantity pairs), and there are multiple such lists for each category of items
    ArrayList<HashMap<String, Integer>> itemList = new ArrayList<>();

    // 2d array of coordinates of all the item locations (taken from ItemsList constructor declaration)
    String itemChosen;
    public Bag() {//ArrayList<HashMap<String, Integer>> itemList) {
        
        // later convert this to arraylist of objects instead, so it can do the same as Party
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        itemList.add(map);

        map = new HashMap<>(); // old map will still change in arraylist if changed after it was added, so make new map
        map.put("a", 3);
        map.put("b", 2);
        map.put("c", 1);
        itemList.add(map);

        map = new HashMap<>();
        map.put("a", 2);
        map.put("b", 1);
        map.put("c", 3);
        itemList.add(map);
        ///
        this.getImage().scale(800, 600);
    }

    public void act() 
    {
        prepare(listLocations());
    }   

    // initialises world, then prepares by creating the vategory choice bar of the bag
    public void prepare(int[][] itemLocations) {
        if(init) {
            world = (MyWorld)getWorld();
            init = false;
            ///////

            categories = new BagCategories(itemList, itemLocations, 100, 300); // this is where location of category object is chosen
            world.addObject(categories, categories.getX(), categories.getY());
        }
    }

    private int[][] listLocations() {
        //boolean if statement here for whether it's bag or party

        // location of the future ItemsList object (derived from first item location again in BagCategories class)
        int itemX = 400;
        int itemY = 200;

        // list all the possible (maximum) y values in order
        int itemY1 = itemY - 60;
        int itemY2 = itemY1 + 40;
        int itemY3 = itemY2 + 40;
        int itemY4 = itemY3 + 40;
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
                        System.out.println(name + " is all gone");
                    }
                    amount = -1; // indicator for outer loop to break after this
                    break;
                }
            }
            if(amount == -1) break;
        }
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

    public void removeEverything() {
        ArrayList<Class> bagClasses = new ArrayList<>();
        bagClasses.add(ItemSelection.class); // itemselection checks for objects of other classes in its act(), so should bed first
        bagClasses.add(BagCategories.class);
        bagClasses.add(ItemsList.class);
        bagClasses.add(Item.class);
        bagClasses.add(ItemDescription.class);
        for(Class c: bagClasses)
            world.removeObjects(world.getObjects(c));
        Turns.itemList = itemList;
        world.removeObject(this);
    }
}
