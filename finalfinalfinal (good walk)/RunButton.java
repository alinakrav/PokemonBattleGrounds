import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class exits the battle world when pressed, allowing the user to escape the current battle, 
 * and in this case, the current enemy (since they're randomly made on instantiation of the Battle world).
 * 
 * @author Alina Kravchenko
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
        Greenfoot.setWorld(new ScrollingWorld(((Battle)getWorld()).beatenTrainers, ((Battle)getWorld()).x, ((Battle)getWorld()).y, ((Battle)getWorld()).bag, ((Battle)getWorld()).party));
    }
}
