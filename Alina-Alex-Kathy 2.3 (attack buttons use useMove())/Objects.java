import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;
/**
 * Write a description of class Objects here.
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Objects extends Actor
{
    /**
     * Act - do whatever the Objects wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    } 

    public void trainerCollision()
    {
        battle();
    }

    public void battle(){
        // the map's current location 
        int currentX = ((ScrollingWorld)getWorld()).locationX;
        int currentY = ((ScrollingWorld)getWorld()).locationY; 
        // intersection
        Player p1 = (Player)getOneIntersectingObject (Player.class);
        if (p1 != null)
        { 
            //press space to enter the trainerBattle  
            if (ScrollingWorld.switched == false){
                TrainerBattleWorld t = new TrainerBattleWorld(currentX, currentY, "trainer");
                Greenfoot.setWorld(t);
            }
            else{
                avoidCollision(p1, 10);
                // allows the player to enter the battle for several times at the same trainer
                ScrollingWorld.switched = false;
            }
        }
    }

    public void obstacleCollision()
    {
        Player p1 = (Player)getOneIntersectingObject (Player.class);
        if (p1 != null)
        {  
            avoidCollision(p1, 6);
        }
    }
    
    /**
     * set the player d location backword if it thouches the obstacle
     */  
    public void avoidCollision(Actor p1, int d){
        if(p1.getX() <= this.getX()){
            p1.setLocation(p1.getX() - d, p1.getY());
        }   
        
        else if(p1.getY() >= this.getY()){
            p1.setLocation(p1.getX(), p1.getY() + d);
        }
        
        else if(p1.getX() > this.getX()){
            p1.setLocation(p1.getX() + d, p1.getY());
        }

        else if(p1.getY() < this.getY()){
            p1.setLocation(p1.getX(), p1.getY() - d);
        }
    }
}
