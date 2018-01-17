import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    GifImage image = new GifImage("explosion.gif");;
    private int countdown = 60 * 2;
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage(image.getCurrentImage());
        countdown--;
        if(countdown == 0) getWorld().removeObject(this);
    }    
}
