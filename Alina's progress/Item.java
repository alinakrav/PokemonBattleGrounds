//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends Actor
{
    boolean init;
    ///
    
    int x;
    int y;
    public String name;
    int amount;

    public Item(String name, int amount, int x, int y) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.amount = amount;

        assignImage();
    }

    public void act() 
    {
        if(init) {
            if(getWorld().getObjects(ItemDescription.class).size() == 0) { // if no item description object made yet
                ItemDescription itemDescription = new ItemDescription();
                itemDescription.setImagegetWorld().addObject(
    }    

    public void assignImage() {
        setImage("item" + name + ".png");
        if(amount != -1) 
            getImage().drawImage(new GreenfootImage("amount" + amount + ".png"), 0, 0); // note: the x and y are relative to the image, not window coordinates
    }
    
    public void 
}
