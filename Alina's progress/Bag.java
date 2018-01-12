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

    String itemChosen;

    public Bag() {//ArrayList<HashMap<String, Integer>> itemList) {
        ///
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

        this.itemList = itemList;
        this.getImage().scale(800, 600);
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

            categories = new BagCategories(itemList, 100, 300);
            world.addObject(categories, categories.getX(), categories.getY());
        }
    }

    public void itemChosen(String item) {
        itemChosen = item;
        removeEverything();
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
        bagClasses.add(ItemSelection.class); // itemselection checks for objects of other classes in its act(), so should be removed first
        bagClasses.add(BagCategories.class);
        bagClasses.add(ItemsList.class);
        bagClasses.add(Item.class);
        bagClasses.add(ItemDescription.class);
        for(Class c: bagClasses)
            world.removeObjects(world.getObjects(c));
        world.removeObject(this);
    }
}
