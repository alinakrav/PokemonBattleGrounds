import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.ArrayList;

/**
 * Write a description of class PartyTag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PartyTag extends Actor
{
    boolean init = true; // variable to allow you to initialise some actions
    MyWorld world; // this will keep track of the world (must be declared as instance variable)
    ///////////
    Pokemon pokemon;
    int indexInParty;
    int x, y, width, height;
    double pixelsPerHealthPoint; // can only have declaration outside constructor, no separate initialisation
    int health;
    int healthBarHeight = 12; 
    int healthBarWidth = 137;

    GreenfootImage hp, frame, frameHover, stats;

    PartyTag(Pokemon pokemon, int indexInParty, int x, int y) {
        this.indexInParty = indexInParty;
        pokemon.setTag(this);
        this.x = x;
        this.y = y;
        getImage().scale(200, 60);
        width = getImage().getWidth();
        height = getImage().getHeight();

        this.pokemon = pokemon;
        health = pokemon.getCurHealth();
        pixelsPerHealthPoint = (double)healthBarWidth / pokemon.getHealth(); // width divided by max health

        hp = new GreenfootImage(width, height);
        frame = new GreenfootImage(width, height);
        frameHover = new GreenfootImage(width, height);
        stats = new GreenfootImage(width, height);
    }

    public void act() 
    {
        prepare();
    }    

    public void whenHovered() {
        for(PartyTag tag : world.getObjects(PartyTag.class)) {
            if(tag != this)
                tag.drawTag(false);
        }
        drawTag(true);
    }

    // this method initialises world variable, and then does whatever needs to be done on instantiation 
    public void prepare() {
        if(init) {
            world = (MyWorld)getWorld();
            init = false;
            //////////

            drawComponents();
            drawTag(false); // not hovered at the beginning
        }
    }

    // this method is called when the button is clicked. It is defined in the subclasses 
    // for functionality that is specific to that button type.
    public void select() {
        // delete everything created from the party, hide the party pokemon
        world.getObjects(Party.class).get(0).removeEverything();
        //set the player to the chosen pokemon
        world.player = pokemon;
        // for all pokemon in party except the player
        for(PartyTag tag : world.getObjects(PartyTag.class)) { 
            if(tag.getPokemon() != world.player) {
                // delete pokemon from WORLD, not from array
                world.removeObject(tag.getPokemon());
            }
        }
        world.removeObjects(world.getObjects(PartyTag.class)); // to avoid immutable list error, don't remove objects while iterating through them
        // go to default battle interface
        goToMenu();
    }

    public void drawComponents() {
        drawHP();
        drawFrame();
        drawFrameHover();
        drawStats();
    }

    // this method redraws the image of the health bar as health decreases
    public void drawHP(){
        // black background rectangle drawn first, which is visible behind a non-full HP bar
        hp.setColor(Color.BLACK);
        // THIS IS WHERE THE LOCATION SHOULD BE SET, THE IMAGE SIZE SHOULDN'T DETERMINE THE RECTANGLE SIZE
        hp.fillRect(50, 40, (int)healthBarWidth, healthBarHeight);
        hp.setColor(Color.GREEN);
        hp.fillRect(50, 40, (int)(health * pixelsPerHealthPoint), healthBarHeight);
    }

    public void drawFrame() {
        frame.drawImage(new GreenfootImage("tagFrame.png"), 0, 0);
    }

    public void drawFrameHover() {
        frameHover.drawImage(new GreenfootImage("tagFrameHover.png"), 0, 0);
    }

    public void drawStats() { 
        stats.drawString(pokemon.getName(), 100, 20);
    }

    public void drawTag(boolean hovered) {
        getImage().drawImage(hp, 0, 0);
        if(!hovered)
            getImage().drawImage(frame, 0, 0);
        else 
            getImage().drawImage(frameHover, 0, 0);
        getImage().drawImage(stats, 0, 0);

       // world.addObject(pokemon, getX(), getY()); // add the pokemon to world
        pokemon.tagView(20, pokemon.getX(), pokemon.getY());
    }

    private void goToMenu() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(new FightButton());
        buttons.add(new PokemonButton());
        buttons.add(new BagButton());
        buttons.add(new RunButton());
        for(Button button : buttons)
            world.addObject(button, button.x, button.y);
        world.addObject(new Selection(buttons, true, buttons.get(0)), buttons.get(0).quadrants[0][0], buttons.get(0).quadrants[0][1]);
    }

    public void hover() {
        for(PartyTag tag : world.getObjects(PartyTag.class)) {
            if(tag != this) 
                tag.drawTag(false);
        }
        drawTag(true);
    }

    public String getName() {
        return pokemon.getName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public int getPokemonIndex() {
        return indexInParty;
    }
}
