import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class KeyReader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KeyReader extends Actor
{
    private String key;
    Object source;
    boolean set;

    public KeyReader(Object source) {
        this.source = source;
    }

    public void act() {
        if(!set) {
            key = null;
            key = Greenfoot.getKey();
            for(KeyReader instance : getWorld().getObjects(KeyReader.class)) {
                instance.setKey(key);
            }
        }
        set = false;
    }

    public boolean keyIs(String s, Object someSource) {
        if(getWorld() instanceof Intro) {
            return s.equals(key); // no priority order if in Intro world (only one KeyReader)
        }
        else if(getWorld() instanceof ScrollingWorld || getWorld() instanceof Battle) {
            if(getWorld().getObjects(BagCategories.class).isEmpty()) // if bag DOESN'T exist, then only allow any keys (no conflicting classes)
                return s.equals(key);
            else { // if bag exists, only allow keys from bag-related classes to be read	
                return s.equals(key) && (someSource instanceof Selection || someSource instanceof BagCategories);

            }
        }
        else {
            if(!getWorld().getObjects(BagCategories.class).isEmpty())
                return s == key && (someSource instanceof Selection || someSource instanceof BagCategories);
            else
                return s.equals(key);
        }
    }

    // other KeyReader instances can set the key pressed in a single act so all instances get the key pressed with getKey(), not just the first one to call the method
    private void setKey(String key) {
        this.key = key;
        set = true;
    }
    
    public void keyPressed(String s) {
        
}
