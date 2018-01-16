import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class temp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stages extends World
{

    /**
     * Constructor for objects of class temp.
     * 
     */
    public Stages()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 

        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {

        Dragonite dragonite = new Dragonite(5, false);
        addObject(dragonite, 100, 283);
        Pikachu pikachu = new Pikachu(5, true);
        addObject(pikachu, 488, 276);
        pikachu.setLocation(491, 108);
    }

    public void scrollAllObjects(int offsetX, int offsetY)
    {
        for (Actor actor : (java.util.List<Actor>)getObjects(null))
            actor.setLocation(actor.getX() + offsetX, actor.getY() + offsetY);
    }
}
