import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

/**
 * This class is the menu that appears after 'fight' button is pressed.
 * It simply executes the Pokemon move that is defined in its image as its own name.
 * 
 * @author Alina Kravchenko
 */
public class MoveButton extends Button
{
    // constructor makes a button for a specific mode and assigns the main game speed
    public MoveButton(String name) {
        super(); // get functionality of superclass
        this.name = name;
        setImage("Button" + name + ".png"); // set image to the button appropriate to the move
    }   

    public void act() {
        super.act();
    }

    // this method is executed in the parent's act method
    public void select() {
        // moves done from the move button are only done by player
        world.player.move(name);
        world.removeObjects(world.getObjects(Selection.class)); 
        world.removeObjects(world.getObjects(Button.class)); // remove button from world
    }
}
