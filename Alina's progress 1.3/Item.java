//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.ArrayList;

/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends Actor
{
    boolean init = true;
    MyWorld world;
    ///

    int x;
    int y;
    public String name;
    public int amount;

    ItemDescription itemDescription;

    public Item(String name, int amount, int x, int y) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.amount = amount;

        assignImage();
    }

    public void act() 
    {
        prepare();
    }    

    public void prepare() {
        if(init) {
            world = (MyWorld)getWorld();
            init = false;
            ////////
        }
    }

    public void assignImage() {
        setImage("item" + name + ".png");
        if(amount != -1) // if item isn't 'cancel'
            getImage().drawImage(new GreenfootImage("amount" + amount + ".png"), 0, 0); // note: the x and y are relative to the image, not window coordinates
    }

    // set description box's image to current item's description
    public void whenHovered() {
        // ACTUALLY, DELETE THIS IF STATEMENT ONCE YOU MAKE AN ItemDescription_Close_bag.png IMAGE
        if(amount == -1) // if item is 'cancel' 
            world.removeObjects(world.getObjects(ItemDescription.class)); // remove description
        else if(getWorld().getObjects(ItemDescription.class).size() == 0) { // if no description made yet
            itemDescription = new ItemDescription(); // make one new dscription object
            getWorld().addObject(itemDescription, 400, 500);
            whenHovered(); // call back to this method, since next time there will be a description present
        }
        else {// if description exists and item is not 'cancel', then description's image changes 
            world.getObjects(ItemDescription.class).get(0).setImage("ItemDescription_" + name + ".png");
        }
    }

    public void select() {
        world.getObjects(Bag.class).get(0).remapItem(name, --amount); // decrease amount of item chosen
        Turns.player.useItem(name, false);
        
        world.getObjects(Bag.class).get(0).removeEverything(); // exit the Bag object back to main screen
        
        goToMenu();
    }
    
    private void goToMenu() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(new FightButton());
        buttons.add(new PokemonButton());
        buttons.add(new BagButton());
        buttons.add(new RunButton());
        for(Button button : buttons)
            world.addObject(button, button.x, button.y);
        world.addObject(new Selection(buttons, true, buttons.get(0)), buttons.get(0).quadrants[0][0], buttons.get(0).quadrants[0][1]);
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
