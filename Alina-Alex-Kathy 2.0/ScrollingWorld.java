import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Scrollbar;
import java.util.List;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
/**
 *  
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScrollingWorld extends World
{
    // the current location of the map
    public static int locationX, locationY;
    private static int dx, dy;
    public static boolean switched;
    /**
     * the constructor passes through the initial position of the map
     */
    private GreenfootImage scroll = new GreenfootImage("final map.png");  
    public ScrollingWorld(int x, int y, boolean s)
    {
        super(800, 600, 1, false);
        switched = s;
        locationX = x;
        locationY = y;         
        dx = x + 800;
        dy = y + 600;
        prepareObjects(); 
    }

    /**
     * the method sets & repaints the background
     */
    public void setMap(int x, int y){
        locationX = locationX + x; // moves opposite to the player
        locationY = locationY + y;
        getBackground().drawImage(scroll,locationX, locationY );        
    }

    /**
     * add the objects to the world and allow them to scroll together with the background
     */
    public void prepareObjects(){

        addObject(new Grass(), 247+ dx, 381+ dy);
        addObject(new Grass(), 205+ dx, 503+ dy);
        addObject(new Grass(), 694+ dx, 544+ dy); // remember to make it shine before
        //entering the battle field

        addObject(new Sign(), 225 + dx, 76 + dy); // placed in the right place
        addObject(new Sign(), 1161 + dx, 360 + dy);

        addObject(new Pool(), 1260 + dx, 157 + dy);

        addObject(new Tree1x1(), 1510 + dx, 335 + dy); 
        addObject(new Tree1x1(), 775+ dx, 93+ dy); // this is set
        addObject(new Tree1x1(), 407+ dx, 541+ dy); // this is set
        addObject(new Tree1x1(), 488+ dx, 581+ dy);// set
        addObject(new Tree1x1(), -43+ dx, 458+ dy);
        addObject(new Tree1x1(), 935+ dx, 415+ dy);        
        addObject(new Tree1x1(), 122+ dx, 256+ dy);

        addObject(new Tree4x2(), 82 + dx, 456 + dy);

        addObject(new Tree1x4(), -327 + dx, 421 + dy);
        addObject(new Tree1x4(), 82 + dx, 664 + dy);        
        addObject(new Tree1x4(), 813+ dx, 89 + dy);

        addObject(new Tree3x2(), 653 + dx, 374 + dy);
        addObject(new Tree3x2(), -324 + dx, 576 + dy);

        addObject(new Tree1x2(), 813+ dx, 415 + dy);
        addObject(new Tree1x2(), 813+ dx, 335 + dy);
        addObject(new Tree1x2(), 898+ dx, 496 + dy);

        addObject(new Trainer1(), 1050+ dx, 670+ dy );
        addObject(new Trainer2(), 920+ dx, 180+ dy);
        addObject(new Trainer3(), 1300+ dx, 260+ dy);
        addObject(new Trainer4(), -100+ dx , 580+ dy);
        addObject(new Trainer5(), 345 + dx, 60 + dy);

        addObject(new Player(), 400, 300);  
    }
}
