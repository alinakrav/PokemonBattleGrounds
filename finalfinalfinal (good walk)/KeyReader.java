import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class reads the key pressed, and returns it through a method
 * that should be called by the class that instantiated its own KeyReader.
 * 
 * @author Alina Kravchenko
 */
public class KeyReader extends Actor
{
    private String key;
    public KeyReader() {
        setImage("null.png");
    }

    public void act() {
        key = null;
        key = Greenfoot.getKey();
    }

    public boolean keyIs(String s) {
        return s.equals(key);
    }
}

