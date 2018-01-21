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
    ///////////
    private boolean hovered;
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

            /*             
            hp = new GreenfootImage(width, height);
            frame = new GreenfootImage(width, height);
            frameHover = new GreenfootImage(width, height);
            stats = new GreenfootImage(width, height);
             */
        }
        else {
            setImage("null.png");   
            getImage().scale(790, 94);
            width = getImage().getWidth();
            height = getImage().getHeight();

            /*
            close = new GreenfootImage(width, height);  
            closeHover = new GreenfootImage(width, height);
             */
        }
    }

    public void act() 
    {
        init();
    }    

    // this method initialises world variable, and then does whatever needs to be done on instantiation 
    public void init() {
        if(!init) {
            init = true;
            //////////

            //drawComponents();
            drawTag(false); // not hovered at the beginning
        }
    }

    // this method is called when the button is clicked. It is defined in the subclasses 
    // for functionality that is specific to that button type.
    public void select() {
        // delete everything created from the party, hide the party pokemon
        ((Battle)getWorld()).getObjects(Party.class).get(0).removeEverything();
        //set the player to the chosen pokemon
        if(pokemon != null) { // if the tag isn't a 'close' button
            ((Battle)getWorld()).player = pokemon; // make the tag's pokemon the player
            ((Battle)getWorld()).player.battleView();
        }
        // for all pokemon in party except the player
        for(PartyTag tag : ((Battle)getWorld()).getObjects(PartyTag.class)) { 
            if(tag.getPokemon() != ((Battle)getWorld()).player || ((Battle)getWorld()).player.getCurHealth() <= 0)  // if iterated tag doesn't have player, or if player is dead
            // delete pokemon from WORLD, not from array
                ((Battle)getWorld()).removeObject(tag.getPokemon());
            else
                ((Battle)getWorld()).player.battleView();
        }
        // go to default battle interface
        goToMenu();
        // remove everything from the party 
        ((Battle)getWorld()).removeObjects(((Battle)getWorld()).getObjects(PartyTag.class)); // to avoid immutable list error, don't remove objects while iterating through them
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
    public GreenfootImage drawHP(){
        GreenfootImage hp = new GreenfootImage(width, height);
        // black background rectangle drawn first, which is visible behind a non-full HP bar
        hp.setColor(Color.BLACK);
        // THIS IS WHERE THE LOCATION SHOULD BE SET, THE IMAGE SIZE SHOULDN'T DETERMINE THE RECTANGLE SIZE
        hp.fillRect(193, 79, healthBarWidth/2, healthBarHeight);
        hp.setColor(Color.GREEN);
        hp.fillRect(193, 79, (int)(health * pixelsPerHealthPoint), healthBarHeight);
        return hp;
    }

    public GreenfootImage drawFrame() {
        GreenfootImage frame = new GreenfootImage(width, height);
        frame.drawImage(new GreenfootImage("TagStandard.png"), 0, 0);
        return frame;
    }

    public GreenfootImage drawFrameHover() {
        GreenfootImage frameHover = new GreenfootImage(width, height);
        frameHover.drawImage(new GreenfootImage("TagStandardHover.png"), 0, 0);
        return frameHover;
    }

    public GreenfootImage drawStats() { 
        GreenfootImage stats = new GreenfootImage(width, height);
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
        return stats;
    }

    public GreenfootImage drawClose() {
        GreenfootImage close = new GreenfootImage(width, height);
        close.drawImage(new GreenfootImage("TagClose.png"), 0, 0);
        return close;
    }

    public GreenfootImage drawCloseHover() {
        GreenfootImage closeHover = new GreenfootImage(width, height);
        closeHover.drawImage(new GreenfootImage("TagCloseHover.png"), 0, 0);
        return closeHover;
    }

    public void drawTag(boolean hover) { 
        GreenfootImage blank = new GreenfootImage(width, height);
        if(pokemon != null) {
            blank.drawImage(drawHP(), 0, 0);
            //getImage().drawImage(drawHP(), 0, 0);

            if(!hover)
                blank.drawImage(drawFrame(), 0, 0);
            //getImage().drawImage(drawFrame(), 0, 0);
            else 
                blank.drawImage(drawFrameHover(), 0, 0);
            //getImage().drawImage(drawFrameHover(), 0, 0);

            blank.drawImage(drawStats(), 0, 0);
            //getImage().drawImage(drawStats(), 0, 0);
            setImage(blank);
            addToWorld(pokemon, getX() - 95, getY() - 17); // add the pokemon to world
            pokemon.tagView(67, pokemon.getX(), pokemon.getY());
        }
        else {
            if(!hover)
                blank.drawImage(drawClose(), 0, 0);
            //getImage().drawImage(drawClose(), 0, 0);
            else
                blank.drawImage(drawCloseHover(), 0, 0);
            // getImage().drawImage(drawCloseHover(), 0, 0);
            setImage(blank);
        }
    }

    private void goToMenu() {
        ArrayList<Button> buttons = new ArrayList<Button>();
        buttons.add(new FightButton());
        buttons.add(new PokemonButton());
        buttons.add(new BagButton());
        buttons.add(new RunButton());
        for(Button button : buttons) 
            ((Battle)getWorld()).addObject(button, button.x, button.y);
        ((Battle)getWorld()).addObject(new Selection(buttons, true, buttons.get(0)), buttons.get(0).quadrants[0][0], buttons.get(0).quadrants[0][1]);
    }

    public void hover() {
        if(!hovered) {
            drawTag(true);
            this.hovered = true;
            for(PartyTag tag : (((Battle)getWorld()).getObjects(PartyTag.class))) {
                if(tag != this)  {
                    tag.drawTag(false);
                    tag.hovered = false;
                }
            }
        }
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

    public void addToWorld(Actor obj, int x, int y) {
        ((Battle)getWorld()).addObject(obj, x, y);
    }
}
