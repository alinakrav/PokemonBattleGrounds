import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *player needs to 
 * 1. walk around(in a fixed point, only the background scrolls)
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends MainTrainer
{
    public Player(){
        // the player is initially facing front
        setImage("P-front.png");
    }

    public void act() 
    {   
        walk();
        collide();
    } 

    /**
     * the method allows the player to walk around
     */
    public void walk(){ 
        Objects ob = new Objects();
        // only walks when the direction key is pressed and it is not at the edge
        if(Greenfoot.isKeyDown("down")&& scrollOnDirection(DOWN)){
            setImage("P-front.png");
            setLocation(getX(), getY() + 5);
        }
        else if(Greenfoot.isKeyDown("up")&& scrollOnDirection(UP)){
            setImage("P-back.png");
            setLocation(getX(), getY() - 5);
        }
        else if(Greenfoot.isKeyDown("right")&& scrollOnDirection(RIGHT)){
            setImage("p-right.png");
            setLocation(getX() + 5, getY());   
        }
        else if (Greenfoot.isKeyDown("left")&& scrollOnDirection(LEFT)){
            setImage("p-left.png");
            setLocation(getX() - 5, getY());
        }

    }

    public void collide(){
        Objects obj = (Objects)getOneIntersectingObject(Objects.class);
        if(obj != null){ //if we're touching an object
            //We determine what type of object we're colliding with.
            //Depending on the object, we go into a different type of battle
            if(obj instanceof Obstacles || obj instanceof Trees) {
                obj.obstacleCollide(this, 6);
            }
            else if(obj instanceof Trainers) {
                obj.battle(this);
                //obj.grassCollide();
            }
            else if(obj instanceof Grass && ( Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down"))) {
                obj.grassCollide();
            }
        }
    }
}

