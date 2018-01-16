import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

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
    int x, y, width, height;
    int health, pixelsPerHealthPoint; // can only have declaration outside constructor, no separate initialisation
    int healthBarHeight = 12; 
    int healthBarWidth = 137;

    GreenfootImage hp, frame, frameHover, stats;

    PartyTag(Pokemon pokemon, int x, int y) {
        this.x = x;
        this.y = y;
        getImage().scale(200, 60); 
        width = getImage().getWidth();
        height = getImage().getHeight();

        hp = new GreenfootImage(width, height);
        frame = new GreenfootImage(width, height);
        frameHover = new GreenfootImage(width, height);
        stats = new GreenfootImage(width, height);

        this.pokemon = pokemon;
        health = pokemon.getHealth();
        pixelsPerHealthPoint = healthBarWidth / health;
    }

    public void act() 
    {
        prepare();
    }    

    public void whenHovered() {
        for(PartyTag tag : world.getObjects(PartyTag.class)) {
            if(tag.pokemon != null) 
                tag.setImage(tag.getName() + "Tag.png");
        }
        setImage(pokemon.getName() + "TagHover.png");
    }

    // this method is called when the button is clicked. It is defined in the subclasses 
    // for functionality that is specific to that button type.
    public void select() {
        
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

        world.addObject(pokemon, getX(), getY()); // add the pokemon to world
        pokemon.tagView(20, pokemon.getX(), pokemon.getY());
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
}
