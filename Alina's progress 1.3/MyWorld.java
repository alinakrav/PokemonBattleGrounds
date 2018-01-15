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

    // hashmaps of _item name_ and _item quantity_ are in a list (to hold multiple name-quantity pairs), and there are multiple such lists for each category of items
    ArrayList<HashMap<String, Integer>> itemList = new ArrayList<>();
    /// keeps track of current party of pokemon objects
    ArrayList<Pokemon> pokemons = new ArrayList<>();

    public MyWorld()
    {    

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);

        keys = new KeyReader();

        HashMap<String, Integer> map = new HashMap<>();
        //// defining objects in bag///////
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        itemList.add(map);

        map = new HashMap<>(); // old map will still change in arraylist if changed after it was added, so make new map
        map.put("a", 3);
        map.put("b", 2);
        map.put("c", 1);
        itemList.add(map);

        map = new HashMap<>();
        map.put("a", 2);
        map.put("b", 1);
        map.put("c", 3);
        itemList.add(map);
        ///////////

        //// defining pokemon party
        pokemons.add(new Jigglypuff(9, false));
        pokemons.add(new Jigglypuff(9, false));
        pokemons.add(new Jigglypuff(9, false));
        pokemons.add(new Jigglypuff(9, false));
        pokemons.add(new Jigglypuff(9, false));

        ArrayList<Button> buttons = new ArrayList<>();
        PokemonButton pokemonButton = new PokemonButton();
        RunButton runButton = new RunButton();
        BagButton bagButton = new BagButton();
        FightButton fightButton = new FightButton();
        addObject(keys, 0, 0);

        buttons.add(fightButton);
        buttons.add(pokemonButton);
        buttons.add(bagButton);
        buttons.add(runButton);

        addObject(fightButton, fightButton.x, fightButton.y);
        addObject(runButton, runButton.x, runButton.y);
        addObject(bagButton, bagButton.x, bagButton.y);
        addObject(pokemonButton, pokemonButton.x, pokemonButton.y);

        addObject(new Selection(buttons, true, buttons.get(0)), buttons.get(0).quadrants[0][0], buttons.get(0).quadrants[0][1]);

        // temporarily make Turns object here, along with Pokemon
        addObject(new Turns(), 0, 0);
        Pokemon player = new Jigglypuff(9, false);
        addObject(player, 100, 100);
        Turns.player = player;
    }

    public ArrayList<HashMap<String, Integer>> getItemList() {
        return itemList;
    }

    public ArrayList<Pokemon> getParty() {
        return pokemons;
    }

    public KeyReader getKeys() {
        return keys;
    }
}
