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

    public void wildPokemonCollision()
    {
        battle(true);
    }

    public void trainerCollision()
    {
        battle(false);
    }

    public void battleNew(boolean isWildBattle){
        // the map's current location 
        int currentX = ((ScrollingWorld)getWorld()).locationX;
        int currentY = ((ScrollingWorld)getWorld()).locationY; 

        if (isWildBattle == true){ 
            /**
             * if the player gets the chance to battle(the button does not exist in the world), grass turns lighter
             * --> appear a button and ask if the player wants to battle
             * --> if the object is clicked
             * --> go into the battle (entrance: grass)
             * 
             * else, the player will just walk around the class with one button around,the button disappear when the player is off the grass
             *
             * ScrollingWorld.switched == false
             */
            if (getWorld().getObjects(buttonE.class) != null){                    
                // press space to enter the wildPokemonBattle  
                if (Greenfoot.getRandomNumber(10) <= 2){
                    GreenfootImage lighterGrass = new GreenfootImage("grass 2x2 lighter.png");
                    //setImage(lighterGrass);
                    buttonE e = new buttonE();
                    getWorld().addObject(e, 200, 200);
                    if (isButtonClicked(e)){
                        // passes the current map location and store it in the battle world,so the 
                        // player can stay in the same spot when it returns from the battle
                        // "grass" indicates the battle's entrance --> directs to the wildPokemon battle
                        getWorld().removeObject(e);
                        TrainerBattleWorld w = new TrainerBattleWorld(currentX, currentY, "grass");
                        Greenfoot.setWorld(w);
                    }
                } /**
                else if (ScrollingWorld.switched == true){
                avoidCollision(p1, 50);
                // allows the player to enter the battle for several times at the same trainer
                ScrollingWorld.switched = false;
                }*/
            }

        }
        else{ 
            //press space to enter the trainerBattle  
            if (ScrollingWorld.switched == false){
                TrainerBattleWorld t = new TrainerBattleWorld(currentX, currentY, "trainer");
                Greenfoot.setWorld(t);
            }
            else{
                //avoidCollision(p1, 6);
                // allows the player to enter the battle for several times at the same trainer
                ScrollingWorld.switched = false;
            }

        }

    }

    public void grassCollision(){
        /**
         * if the player gets the chance to battle(the button does not exist in the world), grass turns lighter
         * --> appear a button and ask if the player wants to battle
         * --> if the object is clicked
         * --> go into the battle (entrance: grass)
         * 
         * else, the player will just walk around the class with one button around,the button disappear when the player is off the grass
         *
         * ScrollingWorld.switched == false
         */
        if (getWorld().getObjects(buttonE.class) != null){                    
            // press space to enter the wildPokemonBattle  
            if (Greenfoot.getRandomNumber(10) <= 2){
                GreenfootImage lighterGrass = new GreenfootImage("grass 2x2 lighter.png");
                //setImage(lighterGrass);
                buttonE e = new buttonE();
                getWorld().addObject(e, 200, 200);
                if (isButtonClicked(e)){
                    // passes the current map location and store it in the battle world,so the 
                    // player can stay in the same spot when it returns from the battle
                    // "grass" indicates the battle's entrance --> directs to the wildPokemon battle
                    getWorld().removeObject(e);
                    TrainerBattleWorld w = new TrainerBattleWorld(currentX, currentY, "grass");
                    Greenfoot.setWorld(w);
                }
            } /**
            else if (ScrollingWorld.switched == true){
            avoidCollision(p1, 50);
            // allows the player to enter the battle for several times at the same trainer
            ScrollingWorld.switched = false;
            }*/
        }

    }

    public void battle(boolean isWildBattle){
        Player p1 = (Player)getOneIntersectingObject(Player.class);
        // the map's current location 
        int currentX = ((ScrollingWorld)getWorld()).locationX;
        int currentY = ((ScrollingWorld)getWorld()).locationY; 

        if (p1 != null)
        { 
            if (isWildBattle == true){ 
                /**
                 * if the player gets the chance to battle(the button does not exist in the world), grass turns lighter
                 * --> appear a button and ask if the player wants to battle
                 * --> if the object is clicked
                 * --> go into the battle (entrance: grass)
                 * 
                 * else, the player will just walk around the class with one button around,the button disappear when the player is off the grass
                 *
                 * ScrollingWorld.switched == false
                 */
                if (getWorld().getObjects(buttonE.class) != null){                    
                    // press space to enter the wildPokemonBattle  
                    if (Greenfoot.getRandomNumber(10) <= 2){
                        GreenfootImage lighterGrass = new GreenfootImage("grass 2x2 lighter.png");
                        //setImage(lighterGrass);
                        buttonE e = new buttonE();
                        getWorld().addObject(e, 200, 200);
                        if (isButtonClicked(e)){
                            // passes the current map location and store it in the battle world,so the 
                            // player can stay in the same spot when it returns from the battle
                            // "grass" indicates the battle's entrance --> directs to the wildPokemon battle
                            getWorld().removeObject(e);
                            TrainerBattleWorld w = new TrainerBattleWorld(currentX, currentY, "grass");
                            Greenfoot.setWorld(w);
                        }
                    } /**
                    else if (ScrollingWorld.switched == true){
                    avoidCollision(p1, 50);
                    // allows the player to enter the battle for several times at the same trainer
                    ScrollingWorld.switched = false;
                    }*/
                }
            }
        }
        else{ 
            //press space to enter the trainerBattle  
            if (ScrollingWorld.switched == false){
                Battle battleWorld = new Battle(currentX, currentY, "trainer");
                Greenfoot.setWorld(battleWorld);
            }
            else{
                //avoidCollision(p1, 6);
                // allows the player to enter the battle for several times at the same trainer
                ScrollingWorld.switched = false;
            }

        }

    }

    public boolean isButtonClicked(Actor button){
        if(Greenfoot.mouseClicked(button)) {
            return true;
        }
        return false;
    }


    public void obstacleTreeCollision(Actor p1, int d){
        if(p1.getX() < this.getX()){
            p1.setLocation(p1.getX() - d, p1.getY());
        }            
        else if(p1.getY() < this.getY()){
            p1.setLocation(p1.getX(), p1.getY() - d);
        }
        else if(p1.getX() > this.getX()){
            p1.setLocation(p1.getX() + d, p1.getY());
        }
        else{
            p1.setLocation(p1.getX(), p1.getY() + d);
        }

    }
}
