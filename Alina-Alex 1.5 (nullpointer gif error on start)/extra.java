import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class extra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class extra extends Actor
{
    HashMap<String, Pokemon> map = new HashMap<String,Pokemon>();
    public extra(){
        map.put("Jigglypuff", new Jigglypuff(6, false));
    }
    /**
     * Act - do whatever the extra wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }    
    public void removeJiggly(){
        getWorld().removeObject(map.get("Jigglypuff"));
        
    }
    public void addJiggly(){
        getWorld().addObject(map.get("Jigglypuff"), 10,10);
    }
    public void turn(int x, int y){
        turnTowards(x,y);
    }
}
