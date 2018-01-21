import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color; Kathy changes this becasue of her greenfoot version
import java.util.ArrayList;
//import greenfoot.Color;
import java.awt.Color;
import java.awt.Font;

/**
 * Write a description of class PartyTag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PartyTag extends Actor
{
    boolean init = true; // variable to allow you to initialise some actions
    Battle world; // this will keep track of the world (must be declared as instance variable)
    ///////////
    Pokemon pokemon;
    int x, y, width, height;
    double pixelsPerHealthPoint; // can only have declaration outside constructor, no separate initialisation
    int health;
    int healthBarHeight = 12; 
    int healthBarWidth = 144;

    GreenfootImage hp, frame, frameHover, stats, close, closeHover;

    PartyTag(Pokemon pokemon, int x, int y) {
        this.x = x;
        this.y = y;
        setLocation(x, y);
        if(pokemon != null) { // for every tag except 'close party' one 
            setImage("null.png");
            getImage().scale(384, 147);
            width = getImage().getWidth();
            height = getImage().getHeight();

            pokemon.setTag(this);
            this.pokemon = pokemon;
            health = pokemon.getCurHealth();
            pixelsPerHealthPoint = (double)healthBarWidth / pokemon.getHealth(); // width divided by max health

            hp = new GreenfootImage(width, height);
            frame = new GreenfootImage(width, height);
            frameHover = new GreenfootImage(width, height);
            stats = new GreenfootImage(width, height);
        }
        else {
            setImage("null.png");   
            getImage().scale(790, 94);
            width = getImage().getWidth();
            height = getImage().getHeight();

            close = new GreenfootImage(width, height);  
            closeHover = new GreenfootImage(width, height);
        }
    }

    public void act() 
    {
        init();
    }    

    // this method initialises world variable, and then does whatever needs to be done on instantiation 
    public void init() {
        if(!init) {
            world = (Battle)getWorld();
            init = true;
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
        if(pokemon != null) { // if the tag isn't a 'close' button
            world.player = pokemon; // make the tag's pokemon the player
            world.player.battleView();
        }
        // for all pokemon in party except the player
        for(PartyTag tag : world.getObjects(PartyTag.class)) { 
            if(tag.getPokemon() != world.player || world.player.getCurHealth() <= 0)  // if iterated tag doesn't have player, or if player is dead
            // delete pokemon from WORLD, not from array
                world.removeObject(tag.getPokemon());
            else
                world.player.battleView();
        }
        world.removeObjects(world.getObjects(PartyTag.class)); // to avoid immutable list error, don't remove objects while iterating through them
        // go to default battle interface
        goToMenu();
    }

    public void drawComponents() {
        if(pokemon != null) {
            drawHP();
            drawFrame();
            drawFrameHover();
            drawStats();
        }
        else {
            drawClose();
            drawCloseHover();
        }
    }

    // this method redraws the image of the health bar as health decreases
    public void drawHP(){
        // black background rectangle drawn first, which is visible behind a non-full HP bar
        hp.setColor(Color.BLACK);
        // THIS IS WHERE THE LOCATION SHOULD BE SET, THE IMAGE SIZE SHOULDN'T DETERMINE THE RECTANGLE SIZE
        hp.fillRect(193, 79, healthBarWidth/2, healthBarHeight);
        hp.setColor(Color.GREEN);
        hp.fillRect(193, 79, (int)(health * pixelsPerHealthPoint), healthBarHeight);
    }

    public void drawFrame() {
        frame.drawImage(new GreenfootImage("TagStandard.png"), 0, 0);
    }

    public void drawFrameHover() {
        frameHover.drawImage(new GreenfootImage("TagStandardHover.png"), 0, 0);
    }

    public void drawStats() { 

        stats.setColor(Color.WHITE);
        // draw the name (for charmander's long name, make font smaller)
        if(!pokemon.getName().equals("Charmander")) {
            stats.setFont(new java.awt.Font("Dialog", Font.PLAIN, 40));
            stats.drawString(pokemon.getName(), 170, 63);
        }
        else {
            stats.setFont(new java.awt.Font("Dialog", Font.PLAIN, 35));
            stats.drawString(pokemon.getName(), 153, 63);
        }
        //draw the level
        stats.setFont(new java.awt.Font("Dialog", Font.PLAIN, 35));
        stats.drawString(("Lv." + pokemon.getLevel()), 35, 125);

        stats.drawString((pokemon.getCurHealth() + "  " + pokemon.getHealth()), 225, 128);
    }

    public void drawClose() {
        close.drawImage(new GreenfootImage("TagClose.png"), 0, 0);
    }

    public void drawCloseHover() {
        closeHover.drawImage(new GreenfootImage("TagCloseHover.png"), 0, 0);
    }

    public void drawTag(boolean hovered) { 
        if(pokemon != null) {
            setImage("null.png");
            getImage().scale(384, 147);

            getImage().drawImage(hp, 0, 0);

            if(!hovered)
                getImage().drawImage(frame, 0, 0);
            else 
                getImage().drawImage(frameHover, 0, 0);

            getImage().drawImage(stats, 0, 0);
            world.addObject(pokemon, getX() - 95, getY() - 17); // add the pokemon to world
            pokemon.tagView(67, pokemon.getX(), pokemon.getY());
        }
        else {
            setImage("null.png");
            getImage().scale(790, 94);
            if(!hovered)
                getImage().drawImage(close, 0, 0);
            else
                getImage().drawImage(closeHover, 0, 0);
        }
    }

    private void goToMenu() {
        ArrayList<Button> buttons = new ArrayList<Button>();
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
}
