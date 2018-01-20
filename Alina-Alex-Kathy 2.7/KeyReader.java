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
    Object source;

    public KeyReader(Object source) {
        this.source = source;
        System.out.println(source);
    }

    public void act() {
        key = Greenfoot.getKey();
    }    

    public boolean keyIs(String s, Object someSource) {
        if(key == null || someSource != source)
            return false;
        else{
            boolean keyIs = s.equals(key);
            key = null;
            return keyIs;
        }
    }
}
