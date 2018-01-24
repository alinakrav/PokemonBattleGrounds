import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * This class moves the map according to the player's location, 
 * which is defined here based on the direction in which he is walking.
 * The directional funcitonality is also used to avoid (not step on) 
 * objects that are considered 'obstacles' (such as Trainers and vegetation).
 * 
 * @author Kathy Zhuang
 */
public class Player extends PlayerDirection
{
    boolean init = true;
    private int lastMove;
    KeyReader keys;

    public Player(){
        // the player is initially facing front
        setImage("P-front.png");
    }

    public void act() 
    {   
        init();
        walk();
        collide();
    } 

    private void init() {
        if(init) {
            init = false;
            if(getWorld() instanceof Battle) {
                keys = ((Battle)getWorld()).getKeys();
            }
            else {
                keys = ((ScrollingWorld)getWorld()).getKeys();
            }
        }
    }

    /**
     * the method allows the player to walk around
     */
    public void walk(){ 
        Objects ob = new Objects();
        if(getWorld().getObjects(Bag.class).isEmpty()) {
            // only walks when the direction key is pressed and it is not at the edge
            if(Greenfoot.isKeyDown("down")&& scrollOnDirection(DOWN)){
                setImage("P-front.png");
                setLocation(getX(), getY() + 5);
                lastMove = 1;
            }
            else if(Greenfoot.isKeyDown("up")&& scrollOnDirection(UP)){
                setImage("P-back.png");
                setLocation(getX(), getY() - 5);
                lastMove = 2;
            }
            else if(Greenfoot.isKeyDown("right")&& scrollOnDirection(RIGHT)){
                setImage("p-right.png");
                setLocation(getX() + 5, getY());   
                lastMove = 3;
            }
            else if (Greenfoot.isKeyDown("left")&& scrollOnDirection(LEFT)){
                setImage("p-left.png");
                setLocation(getX() - 5, getY());
                lastMove = 4;
            }
        }
    }

    /**
     * the method allows the player to walk around
     */
    /*
    public void walk(){ 
    Objects ob = new Objects();
    if(getWorld().getObjects(Bag.class).isEmpty()) {
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
    }
     */

    public void collide(){
        //We determine what type of object we're colliding with.
        //Depending on the object, we go into a different type of battle
        boolean canGoBack = true;
        List foundObjs = getIntersectingObjects(Objects.class);
        // I am getting all the different objects that I am colliding it because you can touch more than one object
        for(int i = 0; i< foundObjs.size();i++){
            Objects obj = (Objects)foundObjs.get(i);
            if(obj instanceof Obstacles || obj instanceof Tree) {
                //you can only collide with solid object at a time
                if(canGoBack){
                    canGoBack = false;
                    // this basically reverses the previous walking command to help you collide
                    obj.goBack(this,lastMove);
                }
                //obj.obstacleCollide(this, 6);
            }
            else if(obj instanceof Trainer) {
                obj.battle(this);
                //obj.grassCollide();
            }
            else if(obj instanceof Grass && ( Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down"))) {
                obj.grassCollide();
            }
        }
    }

    /*
    public void collide(){
    Objects obj = (Objects)getOneIntersectingObject(Objects.class);
    if(obj != null){ //if we're touching an object
    //We determine what type of object we're colliding with.
    //Depending on the object, we go into a different type of battle
    if(obj instanceof Obstacles || obj instanceof Tree) {
    obj.obstacleCollide(this, 6);
    }
    else if(obj instanceof Trainer) {
    if(!((ScrollingWorld)getWorld()).beatenTrainers.contains(((Trainer)obj).number)) 
    obj.battle(this);
    else
    obj.obstacleCollide(this, 6);
    }
    // to trigger the wild battle, the player has to touch the grass object and walking at the same time  
    else if(obj instanceof Grass && ( Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down"))) {
    obj.grassCollide();
    }
    }
    }
     */

}

