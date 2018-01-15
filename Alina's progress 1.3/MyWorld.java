import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
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
    public MyWorld()
    {    

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);

        keys = new KeyReader();

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

    public KeyReader getKeys() {
        return keys;
    }
}
