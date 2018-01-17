import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{   
    KeyReader keys;

    public Pokemon player, enemy;
    // hashmaps of _item name_ and _item quantity_ are in a list (to hold multiple name-quantity pairs), and there are multiple such lists for each category of items
    ArrayList<HashMap<String, Integer>> bag = new ArrayList<HashMap<String, Integer>>();
    /// keeps track of current party of pokemon objects
    ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        setBackground(new GreenfootImage("battleScreen.png"));

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
        pokemons.add(new Jigglypuff(9, false));
        pokemons.add(new Charmander(9, false));
        pokemons.add(new Pikachu(9, false));
        pokemons.add(new Dragonite(9, false));
        pokemons.add(new Pikachu(9, false));
        pokemons.add(new Mudkip(9, false));
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
        String[] enemies = {"Charmander", "Dragonite", "Pikachu", "Jigglypuff", "Mudkip", "Gyarados"}; // define all possible enemies
        int randInd = (int)(Math.random() * 5 + 1); // generate random index out of the above array (min = 0, max = 5)
        // make instance of the enemy classes described in the array, based on the random index
        if(enemies[randInd].equals("Charmander"))
            return new Charmander(9, true);
        else if(enemies[randInd].equals("Dragonite"))
            return new Dragonite(9, true);
        else if(enemies[randInd].equals("Pikachu"))
            return new Pikachu(9, true);
        else if(enemies[randInd].equals("Jigglypuff"))
            return new Jigglypuff(9, true);
        else if(enemies[randInd].equals("Mudkip"))
            return new Mudkip(9, true);
        else if(enemies[randInd].equals("Gyarados"))
            return new Gyarados(9, true);
        return null; // return nothing if index doesn't point to String
    }

    public KeyReader getKeys() {
        return keys;
    }
}
