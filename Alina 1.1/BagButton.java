import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This subclass is used to make buttons that, when pressed, instantiate a game with
 * the speed that is assigned to each given mode. Passing it a different mode changes 
 * the speed of the game that starts when the specific play button is pressed.
 * 
 * @author Alex, Kathy, Alina 
 * @version November 9th, 2017
 */
public class BagButton extends Button
{
    public int x = q1X;
    public int y = q1Y;
    
    // constructor makes a button for a specific mode and assigns the main game speed
    public BagButton() {
        super(); // get functionality of superclass
        //setImage(new GreenfootImage("AttackButton.png")); // set image to the button appropriate to the mode
        //getImage().scale(400, 250);
                    name = "bag";
    }  
    
    public void act() {
        super.act();
    }
    
    // this method is executed in the parent's act method
    public void whenClicked() {
        Bag bag = new Bag();
        world.addObject(bag, world.getWidth()/2, world.getHeight()/2);
        // specific bag stuff
        
        world.removeObjects(world.getObjects(PokemonButton.class)); 
        world.removeObjects(world.getObjects(FightButton.class)); // have to remove this button LAST
        world.removeObjects(world.getObjects(RunButton.class)); 
        world.removeObjects(world.getObjects(BagButton.class)); 
    }
}

