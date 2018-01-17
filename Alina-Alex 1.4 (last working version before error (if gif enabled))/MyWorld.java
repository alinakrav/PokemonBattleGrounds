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
    ArrayList<HashMap<String, Integer>> bag = new ArrayList<>();
    /// keeps track of current party of pokemon objects
    ArrayList<Pokemon> pokemons = new ArrayList<>();

    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);

        keys = new KeyReader();
        addObject(keys, 0, 0);

        HashMap<String, Integer> map = new HashMap<>();
        //// defining objects in bag///////
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        bag.add(map);

        map = new HashMap<>(); // old map will still change in arraylist if changed after it was added, so make new map
        map.put("a", 3);
        map.put("b", 2);
        map.put("c", 1);
        bag.add(map);

        map = new HashMap<>();
        map.put("a", 2);
        map.put("b", 1);
        map.put("c", 3);
        bag.add(map);
        ///////////

        //// defining pokemon party
        pokemons.add(new Jigglypuff(9, false));
        pokemons.add(new Charmander(9, false));
        pokemons.add(new Dragonite(9, false));
        pokemons.add(new Pikachu(9, false));
        pokemons.add(new Mudkip(9, false));
        player = pokemons.get(0);
        addObject(player, 100, 100); // add player to world

        goToMenu();
    }

    private void goToMenu() {
        ArrayList<Button> buttons = new ArrayList<>();
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

    public void swapParty(int index, Pokemon pokemon) {
        pokemons.set(index, pokemon); // the 'set()' method swaps elements of an arraylist 
    }

    public KeyReader getKeys() {
        return keys;
    }
}
