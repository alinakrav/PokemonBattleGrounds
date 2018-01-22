import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * This class starts playing the background music,
 * and sets images to introduction screens that 
 * decsribe how the game works.
 * It presents the user with an option to play in easy or hard mode,
 * which are presented with buttons on the last screen.
 * 
 */
public class Intro extends World
{
    /**
     * Constructor for objects of class Intro.
     * 
     */
    //whether the first act was played yet
    boolean init;

    //reads keys pressed
    KeyReader keys = new KeyReader();

    // bag and party of the player's game session
    ArrayList<HashMap<String, Integer>> bag = new ArrayList<HashMap<String, Integer>>();
    ArrayList<Pokemon> party = new ArrayList<Pokemon>();

    GreenfootSound song;
    //counts screen number
    int screenCounter = 0;
    int cooldownCount = 0;
    int cooldown = 10;
    //x = -800, y = 600
    //constructor sets the first screen image 
    public Intro()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        addObject(keys, 0, 0);

        //set first screen to background
        setBackground(new GreenfootImage("IntroScreen0.png"));
        getBackground().scale(800, 600);

        // set initial variables of the game
        bag = makeBag();
        party = makeParty();
    }

    public void act() { 
        //do in the first act
        if(!init) {
            song = new GreenfootSound("theme.mp3");
            song.setVolume(30);
            song.playLoop();
            //do not repeat this action for other acts
            init = true;
        }

        //get keyboard key pressed
        if(cooldownCount-- < 0 && keys.keyIs("enter")) {
            if(screenCounter < 2) {
                //set background to new screen number count (next screen in set of screens for intro)
                setBackground(new GreenfootImage("IntroScreen" + ++screenCounter + ".png"));
                getBackground().scale(800, 600);   
            }
            else            //if last screen image was shown, then move onto the main menu
            //set up the difficulty buttons
                Greenfoot.setWorld(new ScrollingWorld(-800, -600, false, bag, party));
            // reset cooldown 
            cooldownCount = cooldown;
        }
    }

    public ArrayList<HashMap<String, Integer>> makeBag() {

        //// defining objects in bag///////
        /*
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
         */

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        bag.add(map);
        map = new HashMap<String, Integer>(); // old map will still change in arraylist if changed after it was added, so make new map
        bag.add(map);
        map = new HashMap<String, Integer>();
        map.put("Pokeball", 3);
        bag.add(map);
        map = new HashMap<String, Integer>();
        bag.add(map);
        map = new HashMap<String, Integer>();
        bag.add(map);
        map = new HashMap<String, Integer>();
        bag.add(map);
        map = new HashMap<String, Integer>();
        bag.add(map);
        map = new HashMap<String, Integer>();
        bag.add(map);
        /*
        map.put("a", 3);
        map.put("b", 2);
        map.put("c", 1);
         */

        /*
        map.put("b", 1);
        map.put("c", 3);
         */

        ///////////
        return bag;
    }

    public ArrayList<Pokemon> makeParty() {
        //// defining pokemon party
        party.add(new Charmander(3, false));
        party.add(new Snorlax(3, false));
        party.add(new Mudkip(3, false));
        party.add(new Gyarados(3, false));
        party.add(new Dragonite(3, false));

        return party;
    }
}
