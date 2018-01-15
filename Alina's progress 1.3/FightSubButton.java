import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This subclass is used to make buttons that, when pressed, instantiate a game with
 * the speed that is assigned to each given mode. Passing it a different mode changes 
 * the speed of the game that starts when the specific play button is pressed.
 * 
 * @author Alex, Kathy, Alina 
 * @version November 9th, 2017
 */
public class FightSubButton extends Button
{
    // constructor makes a button for a specific mode and assigns the main game speed
    public FightSubButton(String name) {
        super(); // get functionality of superclass
        this.name = name;
        setImage(new GreenfootImage(name + "Button.png")); // set image to the button appropriate to the move
        //getImage().scale(400, 250);
    }   

    public void act() {
        super.act();
    }

    // this method is executed in the parent's act method
    public void select() {
        Turns.player.useMove(name, false); // moves done from the move button are only done by player
        world.removeObjects(world.getObjects(Selection.class)); 
        world.removeObjects(world.getObjects(Button.class)); // remove button from world
    }
}
