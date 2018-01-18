import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * player needs to 
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
            if ((Grass)getOneIntersectingObject (Grass.class) != null){
                changeWorld();
            }
        }
        else if(Greenfoot.isKeyDown("up")&& scrollOnDirection(UP)){
            setImage("P-back.png");
            setLocation(getX(), getY() - 5);

            if ((Grass)getOneIntersectingObject (Grass.class) != null){
                changeWorld();
            }
        }
        else if(Greenfoot.isKeyDown("right")&& scrollOnDirection(RIGHT)){
            setImage("p-right.png");
            setLocation(getX() + 5, getY());       

            if ((Grass)getOneIntersectingObject (Grass.class) != null){
                changeWorld();
            }
        }
        else if (Greenfoot.isKeyDown("left") && scrollOnDirection(LEFT)){
            setImage("p-left.png");
            setLocation(getX() - 5, getY());

            if ((Grass)getOneIntersectingObject (Grass.class) != null){
                changeWorld();
            }
        }

    }

    public void changeWorld(){
        if (Greenfoot.getRandomNumber(100) <= 2){
            Battle w = new Battle(((ScrollingWorld)getWorld()).locationX, ((ScrollingWorld)getWorld()).locationY, true);
            Greenfoot.setWorld(w);
        }
    }

    public void collide(){
        Objects obj = (Objects)getOneIntersectingObject(Objects.class);
        if(obj != null){ //if we're touching an object
            //We determine what type of object we're colliding with.
            //Depending on the object, we go into a different type of battle
            if(obj instanceof Obstacles) {
                Obstacles obs = (Obstacles)obj;
                obs.collide();
            }
            else if(obj instanceof Trees) {
                Trees tree = (Trees)obj;
                tree.collide();
            }
            else if(obj instanceof Trainers) {
                Trainers trainer = (Trainers)obj;
                trainer.collide();
            }
            else if(obj instanceof Grass) {
                Grass grass = (Grass)obj;
                grass.collide();
            }
        }
    }
}

