import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This subclass is used to make buttons that, when pressed, instantiate a game with
 * the speed that is assigned to each given mode. Passing it a different mode changes 
 * the speed of the game that starts when the specific play button is pressed.
 * 
 * @author Alex, Kathy, Alina 
 * @version November 9th, 2017
 */
public class RunButton extends Button
{
    // constructor makes a button for a specific mode and assigns the main game speed
    public RunButton() {
        super(); // get functionality of superclass
        //setImage(new GreenfootImage("AttackButton.png")); // set image to the button appropriate to the mode
        //getImage().scale(400, 250);
        x = q4X;
        y = q4Y;
        name = "Run";
        setImage(new GreenfootImage("Button" + name + ".png"));
    }   

    public void act() {
        super.act();
    }

    // this method is executed in the parent's act method
    public void select() {        
        Greenfoot.setWorld(new ScrollingWorld(((Battle)getWorld()).x, ((Battle)getWorld()).y, false, ((Battle)getWorld()).bag, ((Battle)getWorld()).party));
    }
}
