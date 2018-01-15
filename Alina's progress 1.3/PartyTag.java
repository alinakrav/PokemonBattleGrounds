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
    int x, y;
    public String name;
    int health, pixelsPerHealthPoint; // can only have declaration outside constructor, no separate initialisation
    int healthBarHeight = 12; 
    int healthBarWidth = 137;

    PartyTag(Pokemon pokemon) {
        getImage().scale(200, 60);
        getImage().drawString(pokemon.getName(), 0, 10); // draws name of pokemon on tag

        health = pokemon.getHealth();
        pixelsPerHealthPoint = healthBarWidth / health;
        drawHealth();
    }

    public void act() 
    {
        prepare();
    }    

    public void whenHovered() {
        for(PartyTag tag : world.getObjects(PartyTag.class)) {
            if(tag.name != null) 
                tag.setImage(tag.name + "Tag.png");
        }
        setImage(name + "TagHover.png");
    }

    // this method is called when the button is clicked. It is defined in the subclasses 
    // for functionality that is specific to that button type.
    public void select() {}

    // this method initialises world variable, and then does whatever needs to be done on instantiation 
    public void prepare() {
        if(init) {
            world = (MyWorld)getWorld();
            init = false;
            //////////
        }
    }

    // this method redraws the image of the health bar as health decreases
    public void drawHealth(){
        // black background rectangle, which is visible behind a non-full HP bar
        GreenfootImage bkgd = new GreenfootImage((int)healthBarWidth, (int)healthBarHeight);
        bkgd.setColor(Color.BLACK);
        bkgd.setColor(Color.BLACK);
        bkgd.fillRect(1, 1, (int)healthBarWidth, healthBarHeight);
        getImage().drawImage(bkgd, 10, 10);

        GreenfootImage healthBar = new GreenfootImage((int)healthBarWidth, (int)healthBarHeight);
        healthBar.setColor(Color.GREEN);
        healthBar.setColor(Color.GREEN);
        healthBar.fillRect(1, 1, (int)(health * pixelsPerHealthPoint), healthBarHeight);
        getImage().drawImage(healthBar, 10, 10);
    }

}
