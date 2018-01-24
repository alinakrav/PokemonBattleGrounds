import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class creates an object with the image of a trainer in the map.
 * All trainers are numbered and have different images.
 * 
 * @author Kathy Zhuang
 */
public class Trainer extends Objects
{
    int number;
    public Trainer(int number) {
        super();
        this.number = number;
        setImage(new GreenfootImage("Trainer" + number + ".png"));
    }
}
