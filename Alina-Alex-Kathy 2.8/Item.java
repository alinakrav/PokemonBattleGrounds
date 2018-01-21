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
    World world;
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
            if(getWorld() instanceof Battle)
                world = (Battle)getWorld();
            else
                world = (ScrollingWorld)getWorld();
            init = false;
        }
    }

    public void assignImage() {
        setImage("Item" + name + ".png");
        if(!name.equals("Close")) // if item isn't 'close' button
            getImage().drawImage(new GreenfootImage("Amount" + amount + ".png"), 0, 0); // note: the x and y are relative to the image, not window coordinates
    }

    // set description box's image to current item's description
    public void hover() {
        if(name.equals("Close")) // if item is 'cancel' 
            world.removeObjects(world.getObjects(ItemDescription.class)); // remove description
        else if(getWorld().getObjects(ItemDescription.class).size() == 0) { // if no description made yet
            itemDescription = new ItemDescription(); // make one new dscription object
            getWorld().addObject(itemDescription, 405, 520);
            hover(); // call back to this method, since next time there will be a description present
        }
        else {// if description exists and item is not 'cancel', then description's image changes 
            world.getObjects(ItemDescription.class).get(0).setImage("ItemDescription" + name + ".png");
        }
    }

    public void select() {
        if(!name.equals("Close")) {
            world.getObjects(Bag.class).get(0).remapItem(name, --amount); // decrease amount of item chosen
            if(world instanceof Battle)
                ((Battle)world).player.useItem(name, false);
            else
                ((ScrollingWorld)world).player.useItem(name, false);
        }
        world.getObjects(Bag.class).get(0).removeEverything(); // exit the Bag object back to main screen

        if(!(world instanceof ScrollingWorld))
            goToMenu();
    }

    private void goToMenu() {
        ArrayList<Button> buttons = new ArrayList<Button>();
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
