import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Introduction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Introduction extends World
{

    /**
     * Constructor for objects of class Introduction.
     * 
     */
    public Introduction()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        System.out.println("intro");
    }
    
    
    public void act(){
        checkMouse();
    }
    
    /**private void exitBattle(){
        ScrollingWorld m = new ScrollingWorld(-800, -600); //x = -800, y = 600
        Greenfoot.setWorld(m);
    }*/
    
    private void checkMouse()
    {
        if(Greenfoot.isKeyDown("left")){
            ScrollingWorld m = new ScrollingWorld(-800, -600, false); //x = -800, y = 600
            Greenfoot.setWorld(m);
        }
    }
    
}
