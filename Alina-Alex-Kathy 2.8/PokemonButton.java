import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class leads the user to the Party interface when pressed.
 * 
 * @author Alina Kravchenko
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
