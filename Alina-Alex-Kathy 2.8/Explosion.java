import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class just animates a GIF file of an explosion whenever it is made. It's called after an attack
 * hits a pokemon and needs to be shown to have touched it before the animation disappears.
 * 
 * @author Alex Do
 */
public class Explosion extends Actor
{
    GifImage image = new GifImage("explosion.gif");;
    private int countdown = 60;
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
