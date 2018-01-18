import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TrainerBattleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TrainerBattleWorld extends World
{
    private static int mapX, mapY;
    private static String theEntrance;
    /**
     * Constructor for objects of class TrainerBattleWorld.
     * 
     */
    public TrainerBattleWorld(int x, int y, String entrance)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        mapX = x;
        mapY = y;
        theEntrance = entrance;
    }

    public void act(){
        switchWorld();
        /*
        if (theEntrance.equals("grass")){
        addObject(new Tree4x2(), 400, 400);
        }
        else if (theEntrance.equals("trainer")){
        addObject(new Pool(), 400, 400);
        }
         */
    }   

    /**
     * switch world if a certain requirements are met
     */
    private void switchWorld()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            int button = mouse.getButton();
            if(button == 1) {
                ScrollingWorld m = new ScrollingWorld(mapX, mapY, true);
                Greenfoot.setWorld(m);
            }
        }

    }

}
