import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;
/**
 * Write a description of class Objects here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Objects extends Actor
{
    /**
     * There are three types of collisions. Grass, trainer, and obstacle/tree collisions.
     * A grass collision results in a battle with a random pokemon.
     * A trainer collision results in a battle with a trainer and their predetermined parties.
     * An obstacle/tree collision is when the player touches the object and is unable to pass it, as players may not move past these objects.
     */

    /**
     * Act - do whatever the Objects wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        /*if(this instanceof Obstacles && this instanceof Trees) obstacleCollision();
        else if(this instanceof Trainers) trainerCollision();
        else if(this instanceof Grass) wildPokemonCollision();
         */
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
