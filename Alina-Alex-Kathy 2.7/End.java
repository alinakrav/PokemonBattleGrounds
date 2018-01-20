import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class displays end screens based on the game's outcome.
 */
public class End extends World
{
    //frames counter until next screen is shown
    String winOrLose;

    // constructor sets the outcome of the game and sets the image
    public End(boolean result) //true for win, false for lose
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1);
        //string stores game outcome to prepend it to generic screen name (Screen.png) and get that screen
        if(result){
            winOrLose = "win";
        } else {
            winOrLose = "lose";
        }
        //set the first image to generic game over screen
        setBackground(new GreenfootImage(winOrLose + "Screen.png"));
    }
}
