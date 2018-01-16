import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class KeyReader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KeyReader extends Actor
{
    String key;
    public void act() {
        key = null;
        key = Greenfoot.getKey();
    }    
    
    public boolean keyIs(String s) {
        return key.equals(s);
    }
    
    public boolean keyNotNull() {
        return key != null;
    }
}
