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
    boolean started;

    // bag and party of the player's game session
    ArrayList<HashMap<String, Integer>> bag = new ArrayList<HashMap<String, Integer>>();
    ArrayList<Pokemon> party = new ArrayList<Pokemon>();

    //counts screen number
    int screenCounter = 0;
    GreenfootSound song;
    int cooldownCount = 0;
    int cooldown = 30;
    ScrollingWorld m; //x = -800, y = 600
    //constructor sets the first screen image 
    public Intro()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        //set first screen to background
        setBackground(new GreenfootImage("IntroScreen0.png"));
        getBackground().scale(800, 600);

        // set initial variables of the game
        bag = makeBag();
        party = makeParty();
        m = new ScrollingWorld(-800, -600, false, bag, party);
    }

    public void act() { 
        cooldownCount++;
        //do in the first act
        if(!started) {
            //play theme song on loop
            song = new GreenfootSound("theme.mp3");
            song.setVolume(10);
            song.playLoop();
            //do not repeat this action for other acts
            started = true;
        }

        //get keyboard key pressed
        String key = Greenfoot.getKey();
        //if it's spacebar
        if(key == "enter" && cooldownCount >= cooldown && screenCounter < 3) {
            //increase the screen number count
            if(screenCounter < 2)
                screenCounter++;
            //set background to new screen number count (next screen in set of screens for intro)
            setBackground(new GreenfootImage("IntroScreen" + screenCounter + ".png"));
            getBackground().scale(800, 600);
            //if last screen image was shown, then move onto the main menu
            if(screenCounter == 2) {
                //set up the difficulty buttons

                Greenfoot.setWorld(m);
                screenCounter++;
            }
            cooldownCount = 0;
        }

    }

    public ArrayList<HashMap<String, Integer>> makeBag() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        //// defining objects in bag///////
        /*
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        */
        bag.add(map);

        map = new HashMap<String, Integer>(); // old map will still change in arraylist if changed after it was added, so make new map
        /*
        map.put("a", 3);
        map.put("b", 2);
        map.put("c", 1);
        */
        bag.add(map);

        map = new HashMap<String, Integer>();
        map.put("Pokeball", 3);
        /*
        map.put("b", 1);
        map.put("c", 3);
        */
        bag.add(map);
        ///////////
        return bag;
    }

    public ArrayList<Pokemon> makeParty() {
        //// defining pokemon party
        party.add(new Charmander(9, false));
        party.add(new Snorlax(9, false));
        party.add(new Mudkip(9, false));
        party.add(new Gyarados(9, false));
        party.add(new Dragonite(9, false));
        party.add(new Jigglypuff(9, false));
        
        return party;
    }
}
