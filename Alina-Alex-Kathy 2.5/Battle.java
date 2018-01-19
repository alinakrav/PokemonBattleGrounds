import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Battle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Battle extends World
{
    KeyReader keys;

    public Pokemon player, enemy;
    int x, y;
    public boolean wildMode;
    // hashmaps of _item name_ and _item quantity_ are in a list (to hold multiple name-quantity pairs), and there are multiple such lists for each category of items
    ArrayList<HashMap<String, Integer>> bag = new ArrayList<HashMap<String, Integer>>();
    /// keeps track of current party of pokemon objects
    ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

    //Basically, turn 0 is when the player can attack. Turn 1 is when the enemy is attacking. Once the person clicks the button for the attack, turn 0 is updated to 1. Then the opponent will attack and turn set it back to turn 0;
    private int turn = 0; 

    public Battle(int x, int y, boolean wildMode)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        setBackground(new GreenfootImage("battleScreen.png"));

        this.x = x;
        this.y = y;
        this.wildMode = wildMode;

        // keyboard reader
        keys = new KeyReader();
        addObject(keys, 0, 0);

        /// helper variable to make the map of items (should be made somewhere else)
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        //// defining objects in bag///////
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        bag.add(map);

        map = new HashMap<String, Integer>(); // old map will still change in arraylist if changed after it was added, so make new map
        map.put("a", 3);
        map.put("b", 2);
        map.put("c", 1);
        bag.add(map);

        map = new HashMap<String, Integer>();
        map.put("a", 2);
        map.put("b", 1);
        map.put("c", 3);
        bag.add(map);
        ///////////

        //// defining pokemon party (should be made somewhere else)
        pokemons.add(new Golbat(9, false));
        pokemons.add(new Oddish(9, false));
        pokemons.add(new Snorlax(9, false));
        pokemons.add(new Mudkip(9, false));
        pokemons.add(new Pikachu(9, false));
        pokemons.add(new Mewtwo(9, false));
        // create and add player and enemy to battle
        player = pokemons.get(0);
        enemy = makeRandomEnemy(); // enemy should be chosen at random
        addObject(enemy, 0, 0);
        addObject(player, 0, 0); // add player to world
        player.battleView(); // you cannot setlocation in the constructor itself, so put pokemon into battleview when they're made here
        enemy.battleView();

        goToMenu();
    }

    private void goToMenu() {
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
        return pokemons;
    }

    public void setParty(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public Pokemon makeRandomEnemy() { // give them variable stats?
        String[] enemies = {"Charmander", "Pikachu", "Articuno", "Mudkip", "Gyarados", "Gengar", "Dragonite", "Jigglypuff", "Snorlax", "Oddish", "Arcanine", "Kyogre", "Golbat", "Arceus", "Tropius", "Mewtwo"}; // define all possible enemies
        int randInd = (int)(Math.random() * (16 + 1)); // generate random index out of the above array (min = 0, max = 5)
        int level = 9;
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
