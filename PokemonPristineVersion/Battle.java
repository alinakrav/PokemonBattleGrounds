import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * This is the second major World class, where 
 * all battles happen when the player enters one from
 * the Scrolling World (map). It keeps track of the bag, 
 * the party, and the trainers that need to inform the ScrollingWorld 
 * class that they're unplayable (they've been beaten). 
 * This game is played in two different modes, Wild and Trainer.
 * These vary by the range of levels that enemy pokemon possess 
 * (wild mode levels are much, much higher, so the user
 * would need to train their party in other battles to 
 * level-up and be able to beat more and more Wild pokemon).
 * 
 * The enemy is defined here, since every battle generates a random one 
 * and it's removed on exit.
 * 
 *  @author Alina Kravchenko
 */
public class Battle extends World
{
    KeyReader keys = new KeyReader();

    public int trainer; // trainer number of the one that you're battling right now
    public ArrayList<Integer> beatenTrainers = new ArrayList<Integer>(); // the trainers that should not trigger battle anymore
    public Pokemon player, enemy;
    public int x, y;
    public boolean wildMode;
    // hashmaps of _item name_ and _item quantity_ are in a list (to hold multiple name-quantity pairs), and there are multiple such lists for each category of items
    public ArrayList<HashMap<String, Integer>> bag = new ArrayList<HashMap<String, Integer>>();
    /// keeps track of current party of pokemon objects
    public ArrayList<Pokemon> party = new ArrayList<Pokemon>();

    //Basically, turn 0 is when the player can attack. Turn 1 is when the enemy is attacking. Once the person clicks the button for the attack, turn 0 is updated to 1. Then the opponent will attack and turn set it back to turn 0;
    private int turn = 0; 

    public Battle(ArrayList<Integer> beatenTrainers, int trainer, int x, int y, boolean wildMode, ArrayList<HashMap<String, Integer>> bag, ArrayList<Pokemon> party)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        setBackground(new GreenfootImage("battleScreen.png"));

        this.x = x;
        this.y = y;
        this.wildMode = wildMode;
        this.trainer = trainer;
        this.beatenTrainers = beatenTrainers;
        this.bag = bag;
        this.party = party;

        // keyboard reader
        addObject(keys, 0, 0);

        // create and add player and enemy to battle
        player = party.get(0);
        enemy = makeRandomPokemon(); // enemy should be chosen at random
        addObject(enemy, 0, 0);
        addObject(player, 0, 0); // add player to world
        player.battleView(); // you cannot setlocation in the constructor itself, so put pokemon into battleview when they're made here
        enemy.battleView();

        goToMenu();
    }

    public void goToMenu() {
        ArrayList<Button> buttons = new ArrayList<Button>();
        buttons.add(new FightButton());
        buttons.add(new PokemonButton());
        buttons.add(new BagButton());
        buttons.add(new RunButton());
        for(Button button : buttons)
            addObject(button, button.x, button.y);
        addObject(new Selection(buttons, true, buttons.get(0)), buttons.get(0).quadrants[0][0], buttons.get(0).quadrants[0][1]);
    }

    public ArrayList<HashMap<String, Integer>> getBag() {
        return bag;
    }

    public void setBag(ArrayList<HashMap<String, Integer>> bag) {
        this.bag = bag;
    }

    public ArrayList<Pokemon> getParty() {
        return party;
    }

    public void setParty(ArrayList<Pokemon> party) {
        this.party = party;
    }

    public Pokemon makeRandomPokemon() { // give them variable stats?
        String[] enemies = {"Charmander", "Pikachu", "Articuno", "Mudkip", "Gyarados", "Gengar", "Dragonite", "Jigglypuff", "Snorlax", "Oddish", "Arcanine", "Kyogre", "Golbat", "Arceus", "Tropius", "Mewtwo"}; // define all possible enemies
        int max = 15;
        int min = 0;
        int randInd = (int)(Math.random()*(max - min + 1) + min); // generate random index out of the above array (min = 0, max = 5)
        int level = 0;
        if(wildMode){
            max = 15;
            min = 1;
            level = (int)(Math.random()*(max - min + 1) + min);
        } else {
            max = 30;
            min = 10;
            level = (int)(Math.random()*(max - min + 1) + min);
        }
        // make instance of the enemy classes described in the array, based on the random index
        if(enemies[randInd].equals("Charmander"))
            return new Charmander(level, true);
        else if(enemies[randInd].equals("Pikachu"))
            return new Pikachu(level, true); 
        else if(enemies[randInd].equals("Articuno"))
            return new Articuno(level, true); 
        else if(enemies[randInd].equals("Mudkip"))
            return new Mudkip(level, true); 
        else if(enemies[randInd].equals("Dragonite"))
            return new Dragonite(level, true);
        else if(enemies[randInd].equals("Jigglypuff"))
            return new Jigglypuff(level, true);
        else if(enemies[randInd].equals("Gyarados"))
            return new Gyarados(level, true); 
        else if(enemies[randInd].equals("Gengar"))
            return new Gengar(level, true); 
        else if(enemies[randInd].equals("Snorlax"))
            return new Snorlax(level, true); 
        else if(enemies[randInd].equals("Oddish"))
            return new Oddish(level, true); 
        else if(enemies[randInd].equals("Arcanine"))
            return new Arcanine(level, true); 
        else if(enemies[randInd].equals("Kyogre"))
            return new Kyogre(level, true); 
        else if(enemies[randInd].equals("Golbat"))
            return new Golbat(level, true); 
        else if(enemies[randInd].equals("Arceus"))
            return new Arceus(level, true); 
        else if(enemies[randInd].equals("Tropius"))
            return new Tropius(level, true); 
        else //if(enemies[randInd].equals("Mewtwo"))
            return new Mewtwo(level, true); 
    }

    public void capturePokemon(Pokemon captured){
        party.add(captured);
        removeObject(captured);
    }

    public KeyReader getKeys() {
        return keys;
    }

    public void setTurn(int turn){
        this.turn = turn;
    }

    public int getTurn(){
        return turn;
    }
}
