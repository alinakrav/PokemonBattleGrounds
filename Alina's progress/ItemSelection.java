//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ItemSelection extends Actor
{
    boolean init = true;
    MyWorld world;
    KeyReader keys;
    //////////

    String key;
    int itemIndex = 0; // always starts at location 0
    ItemsList itemsList;
    public ItemSelection(ItemsList itemsList) {
        this.itemsList = itemsList;
    }

    public void act() 
    {
        prepare();

        if(keys.keyNotNull()) {
            if(keys.keyIs("down") && itemIndex < itemsList.getMaxIndex()) 
                setLocation(getX(), itemsList.getItemLocation(++itemIndex)); 
            else if(keys.keyIs("up") && itemIndex > 0)
                setLocation(getX(), itemsList.getItemLocation(--itemIndex)); 
            else if(keys.keyIs("enter"))
                itemsList.select((getWorld().getObjectsAt(getX(), getY(), Item.class)).get(0));
        }
    }

    public void prepare() {
        if(init) {
            world = (MyWorld)getWorld();
            keys = world.getKeys();
            init = false;
            /////////
        }
    }
}
