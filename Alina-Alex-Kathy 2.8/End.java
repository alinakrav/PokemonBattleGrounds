import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class displays end screens based on the game's outcome.
 * 
 * @author Alex Do, Alina Kravchenko
 */
public class End extends World
{
    //frames counter until next screen is shown
    String result;
    KeyReader keys = new KeyReader();

    // constructor sets the outcome of the game and sets the image
    public End(boolean win) //true for win, false for lose
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        addObject(keys, 0, 0);
        //string stores game outcome to prepend it to generic screen name (Screen.png) and get that screen
        if(win)
            result = "Win";
        else 
            result = "Lose";

        //set the first image to generic game over screen
        setBackground(new GreenfootImage(result + "Screen.png"));
    }

    public void act() {
        if(keys.keyIs("enter"))
            Greenfoot.setWorld(new Intro());
    }
}
