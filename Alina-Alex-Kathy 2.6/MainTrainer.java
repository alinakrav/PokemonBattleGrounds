import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; // use a list to keep track of the trainers
/**
 * Trainer supper class
 * 1. tells the world to scroll or not
 * 2. super class for the player
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
    public class MainTrainer extends Actor
{    
    //define direction
    final int RIGHT = 0;
    final int LEFT = 1;
    final int UP = 2;
    final int DOWN = 3;
        
    /**
     * setLocation() method keeps the main trainer at the middle,moves the 
     * background and other objects instead
     */
    public void setLocation(int x, int y){
        // represntes where the main trainer is trying to move
        int trainerMoveX = getX() - x;
        int trainerMoveY = getY() - y;
        // creates a list that has every object in the game, move them together
        List<Objects> objects = getWorld().getObjects(Objects.class);
        for (int i = 0; i < objects.size(); i ++){
            Actor ob = objects.get(i);
            //moves the object location opposite to the player
            ob.setLocation(ob.getX() + trainerMoveX, ob.getY() + trainerMoveY);             
        }
        // the ScrollingWorld's image moves opposites to the player, it sends the negative 
        // of the player's moving distance
        ((ScrollingWorld)getWorld()).setMap(trainerMoveX, trainerMoveY);
    }
        
     /**
     * indicate whether the player can continue scrolling the world on that direction
     */
    public boolean scrollOnDirection(int direction){
        int currentX = ((ScrollingWorld)getWorld()).locationX;
        int currentY = ((ScrollingWorld)getWorld()).locationY;
        if(direction == LEFT){
            if (currentX < 0)
                return true;
            else 
                return false;
        }
        else if(direction == RIGHT){
            if (currentX > -1800) // can be adjusted based on the picture
                return true;
            else 
                return false;
        }
        else if(direction == UP){
            if (currentY < -350)
                return true;
            else 
                return false;
        }
        else if(direction == DOWN){
            if (currentY > -980)
                return true;
            else 
                return false;
        }
        else 
             return false;
            
    } 
}
