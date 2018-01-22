import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; 
import java.awt.Color;

/**
 * This class is used to make buttons that can be pressed, and 
 * that execute some command when pressed. Such a command is 
 * defined in child classes of the Button class.
 * 
 * @author Alex, Kathy, Alina 
 * @version November 9th, 2017
 */
public class Button extends Actor
{       
    boolean init = true; // variable to allow you to initialise some actions
    Battle world; // this will keep track of the world (must be declared as instance variable)
    ///////////
    public int x, y;
    public String name;

    //quadrant button locations for battle screen
    int q1X = 495;
    int q1Y = 495;
    int q2X = 495;
    int q2Y = 560;   
    int q3X = 695;
    int q3Y = 495;
    int q4X = 695;
    int q4Y = 560;

    //quadrants follow a sideways Z pattern: from top left down, then from top right down
    int[][] quadrants = {{q1X, q1Y}, {q2X, q2Y}, {q3X, q3Y}, {q4X, q4Y}};

    public void act() {
        prepare();      
    }

    // executes the hover 	
    public void hover() {
        for(Button button : world.getObjects(Button.class)) {
            if(button.name != null) 
                button.setImage("Button" + button.name + ".png");
            setImage("ButtonHover" + name + ".png");
        }
    }

    // this method is called when the button is clicked. It is defined in the subclasses 
    // for functionality that is specific to that button type.
    public void select() {}

    // this method initialises world variable, and then does whatever needs to be done on instantiation 
    public void prepare() {
        if(init) {
            world = (Battle)getWorld();
            init = false;
            //////////
        }
    }
}
