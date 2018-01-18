import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Move here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Move extends Actor
{
    private String name;
    GifImage image;

    private int width = 150;
    private int height = 150;

    public Move(String name, boolean enemy){
        this.name = name;
        image = new GifImage(name + ".gif"); 
        image.resizeImages(width, height);
        if(enemy) image.flipImages(); //flip the image depending on whether it is an enemy or not enemy using it
    }

    /**
     * Act - do whatever the Move wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage(image.getCurrentImage());
    }    
}
