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
    public void grassCollide(){
        if (Greenfoot.getRandomNumber(300) <= 2){
            //TrainerBattleWorld w = new TrainerBattleWorld(((ScrollingWorld)getWorld()).locationX,((ScrollingWorld)getWorld()).locationY, "grass");

            Battle battle = new Battle(((ScrollingWorld)getWorld()).locationX,((ScrollingWorld)getWorld()).locationY, true);
            Greenfoot.setWorld(battle);
        }
    }

    public void battle(Player p1){
        // the map's current location 
        int currentX = ((ScrollingWorld)getWorld()).locationX;
        int currentY = ((ScrollingWorld)getWorld()).locationY; 
        // intersection

        if (p1 != null)
        { 
            /*
            //press space to enter the trainerBattle  
            if (ScrollingWorld.switched == false){
            TrainerBattleWorld t = new TrainerBattleWorld(currentX, currentY, "trainer");

            Greenfoot.setWorld(t);
            }
            else{
            obstacleCollide(p1, 30);
            // allows the player to enter the battle for several times at the same trainer
            ScrollingWorld.switched = false;
            }*/
            int offset = 50;
            Battle battle = new Battle(((ScrollingWorld)getWorld()).locationX + offset, ((ScrollingWorld)getWorld()).locationY, true);
            Greenfoot.setWorld(battle);
        }
    }

    /**
     * set the player d location backword if it thouches the obstacle
     */  
    public void obstacleCollide(Actor p1, int d) {
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

