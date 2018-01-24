import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class creates an object with the image of a tree (with numerous available dimensions),
 * which can't be walked on and the player bumps into. 
 * 
 * @author Kathy Zhuang
 */
public class Tree extends Objects
{
    /**
     * Act - do whatever the Trees wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */  
    public Tree(String dimensions) {
        setImage(new GreenfootImage("tree " + dimensions + ".png"));
    }
}
