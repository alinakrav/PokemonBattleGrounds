import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.awt.Color;

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
    public int integ = 1;
    // constructor makes a button for a specific mode and assigns the main game speed
    public FightSubButton(String name) {
        super(); // get functionality of superclass
        this.name = name;
        GreenfootImage img = new GreenfootImage(130, 50);
        img.setColor(Color.WHITE);
        img.fillRect(130, 50, 0, 0);
        setImage(new GreenfootImage("Button" + name + ".png")); // set image to the button appropriate to the move
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
}
