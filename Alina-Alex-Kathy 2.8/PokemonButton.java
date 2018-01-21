import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This subclass is used to make buttons that, when pressed, instantiate a game with
 * the speed that is assigned to each given mode. Passing it a different mode changes 
 * the speed of the game that starts when the specific play button is pressed.
 * 
 * @author Alex, Kathy, Alina 
 * @version November 9th, 2017
 */
public class PokemonButton extends Button
{
    // constructor makes a button for a specific mode and assigns the main game speed
    public PokemonButton() {
        super(); // get functionality of superclass
        //setImage(new GreenfootImage("AttackButton.png")); // set image to the button appropriate to the mode
        //getImage().scale(400, 250);
        x = q2X;
        y = q2Y;
        name = "Pokemon";
        setImage(new GreenfootImage("Button" + name + ".png"));
    }   

    public void act() {
        super.act();
    }

    // this method is executed in the parent's act method
    public void select() {
        Party party = new Party();
        world.addObject(party, world.getWidth()/2, world.getHeight()/2);

        world.removeObjects(world.getObjects(Selection.class)); 
        world.removeObjects(world.getObjects(Button.class));
    }
}
