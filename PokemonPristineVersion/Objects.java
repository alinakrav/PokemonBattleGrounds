import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;
import java.util.*;

/**
 * This class encompasses all objects on the map, which can be interacted with.
 * This means they either perform some fucntion, or the player can collide with them 
 * on contact. They allow the player to enter battle on specific triggers, as well as 
 * not walk over the objects. 
 * 
 * @author Kathy Zhuang
 */
public class Objects extends Actor
{
    public void grassCollide(){
        if (Greenfoot.getRandomNumber(300) <= 2){
            Battle battle = new Battle(((ScrollingWorld)getWorld()).beatenTrainers, 0, ((ScrollingWorld)getWorld()).locationX, ((ScrollingWorld)getWorld()).locationY, true, ((ScrollingWorld)getWorld()).bag, ((ScrollingWorld)getWorld()).party);
            Greenfoot.setWorld(battle);
        }
    }

    public void battle(Player p1){
        /** before the player enters the battle, pass the current map location to the battle world's constructor(set the map's location 50 away from its origin, 
         * so the player does not touch the trainer again when it returns from the battle.
         * also passes the bag and party information to the battle.
         */
        int offset = 50;
        Battle battle = new Battle(((ScrollingWorld)getWorld()).beatenTrainers, ((Trainer)this).number, ((ScrollingWorld)getWorld()).locationX + offset, ((ScrollingWorld)getWorld()).locationY, true, ((ScrollingWorld)getWorld()).bag, ((ScrollingWorld)getWorld()).party);
        Greenfoot.setWorld(battle);
    }

    /**
     * set the player d location backword if it thouches the obstacle
     */  
    public void obstacleCollide(Actor p1, int distance) {
        if(p1.getX() <= this.getX()){
            p1.setLocation(p1.getX() - distance, p1.getY());
        }   

        else if(p1.getY() >= this.getY()){
            p1.setLocation(p1.getX(), p1.getY() + distance);
        }

        else if(p1.getX() > this.getX()){
            p1.setLocation(p1.getX() + distance, p1.getY());
        }

        else if(p1.getY() < this.getY()){
            p1.setLocation(p1.getX(), p1.getY() - distance);
        }
    }

    /**
     * reverses the previous input command the user made to implement object collision
     */
    public void goBack(Actor p1, int move){
        if(move == 1){ // reverses walking down
            p1.setLocation(p1.getX(), p1.getY() - 5);
        }else if(move == 2){ // reverses walking up
            p1.setLocation(p1.getX(), p1.getY() + 5);
        }else if(move == 3){ // reverses walking right
            p1.setLocation(p1.getX() - 5, p1.getY());
        }else if(move == 4){ // reverses walking left
            p1.setLocation(p1.getX() + 5, p1.getY());
        }
    }
}

