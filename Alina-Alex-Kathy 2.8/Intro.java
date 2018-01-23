import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * This class starts playing the background music,
 * and sets images to introduction screens that 
 * decsribe how the game works.
 * 
 * The pokemon party is made here, where 
 * a random pokemon-generating method is used 
 * to decide on 3 random pokemon names with 3 
 * random levels (and thus, stat presets as later
 * defined in the Pokemon class)
 * 
 *  @author Alex Do
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
            song.setVolume(0);
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
            else {           //if last screen image was shown, then move onto the main menu
                // make empty array of beaten trainers ( no one beaten yet)
                ArrayList<Integer> beatenTrainers = new ArrayList<Integer>();
                Greenfoot.setWorld(new ScrollingWorld(beatenTrainers, -800, -600, bag, party));
            }
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
        //// add 3 random pokemon to the player's party
        for(int i = 0; i < 3; i++)
            party.add(makeRandomPokemon());

        return party;
    }

    public Pokemon makeRandomPokemon() { // give them variable stats?
        String[] players = {"Charmander", "Pikachu", "Articuno", "Mudkip", "Gyarados", "Gengar", "Dragonite", "Jigglypuff", "Snorlax", "Oddish", "Arcanine", "Kyogre", "Golbat", "Arceus", "Tropius", "Mewtwo"}; // define all possible players
        int max = 15;
        int min = 0;
        int randInd = (int)(Math.random()*(max - min + 1) + min); // generate random index out of the above array (min = 0, max = 5)
        int level = 0;
        level = (int)(Math.random()*(max - min + 1) + min);
        // make instance of the enemy classes described in the array, based on the random index
        if(players[randInd].equals("Charmander"))
            return new Charmander(level, false);
        else if(players[randInd].equals("Pikachu"))
            return new Pikachu(level, false); 
        else if(players[randInd].equals("Articuno"))
            return new Articuno(level, false); 
        else if(players[randInd].equals("Mudkip"))
            return new Mudkip(level, false); 
        else if(players[randInd].equals("Dragonite"))
            return new Dragonite(level, false);
        else if(players[randInd].equals("Jigglypuff"))
            return new Jigglypuff(level, false);
        else if(players[randInd].equals("Gyarados"))
            return new Gyarados(level, false); 
        else if(players[randInd].equals("Gengar"))
            return new Gengar(level, false); 
        else if(players[randInd].equals("Snorlax"))
            return new Snorlax(level, false); 
        else if(players[randInd].equals("Oddish"))
            return new Oddish(level, false); 
        else if(players[randInd].equals("Arcanine"))
            return new Arcanine(level, false); 
        else if(players[randInd].equals("Kyogre"))
            return new Kyogre(level, false); 
        else if(players[randInd].equals("Golbat"))
            return new Golbat(level, false); 
        else if(players[randInd].equals("Arceus"))
            return new Arceus(level, false); 
        else if(players[randInd].equals("Tropius"))
            return new Tropius(level, false); 
        else //if(enemies[randInd].equals("Mewtwo"))
            return new Mewtwo(level, false); 
    }
}
