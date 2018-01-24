import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class leads the user to the Bag interface when pressed. 
 * 
 * It is unavailable ('greyed out') and cannot be pressed when 
 * the current player pokemon faints (runs out of HP).
 * 
 * @author Alina Kravchenko
 */
public class BagButton extends Button
{
    // constructor makes a button for a specific mode and assigns the main game speed
    public BagButton() {
        super(); // get functionality of superclass
        //getImage().scale(400, 250);
        x = q3X;
        y = q3Y;
        name = "Bag";
        setImage(new GreenfootImage("Button" + name + ".png")); // set image to the button appropriate to the mode
    }  

    public void act() {
        super.act();
    }

    // this method is executed in the parent's act method
    public void select() {
        Bag bag = new Bag(getWorld());
        world.addObject(bag, world.getWidth()/2, world.getHeight()/2);

        world.removeObjects(world.getObjects(Selection.class)); 
                world.removeObjects(world.getObjects(Button.class));
    }
}

