import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

/**
 * Write a description of class BattleTag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleTag extends Actor
{
    boolean init = true;
    Pokemon pokemon;
    String name;
    boolean enemy;
    int maxExp, exp, maxHealth, health, level;
    int width = 304;
    int height = 107;
    double pixelsPerHealthPoint, pixelsPerExp;

    int healthBarHeight = 10; 
    int healthBarWidth = 120;

    int expBarHeight = 7; 
    int expBarWidth = 239;
    public BattleTag(Pokemon pokemon) {
        this.pokemon = pokemon;
        enemy = pokemon.getEnemy();
        name = pokemon.getName();
        maxExp = pokemon.getMaxExpToLevelUp();
        exp = maxExp - pokemon.getExpToLevelUp();
        health = pokemon.getCurHealth();
        maxHealth = pokemon.getHealth();
        pixelsPerHealthPoint = (double)healthBarWidth / maxHealth;
        pixelsPerExp = (double)expBarWidth / maxExp;
    }

    public void act() 
    {
        init();
        health = pokemon.getCurHealth();
        exp = maxExp - pokemon.getExpToLevelUp();
        update();
    }

    public void init() {
        if(init) {
            init = false;
            //if(enemy)
            //   setLocation(200, 200);
            //else
            setLocation(700, 400);
        }
    }

    public void update() {
        GreenfootImage tag = new GreenfootImage(width, height);
        tag.drawImage(drawHpAndExp(), 0, 0);
        if(enemy)
            tag.drawImage(drawFrame(), 0, 0);
        setImage(tag);
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

    public GreenfootImage drawFrame() {
        GreenfootImage frame = new GreenfootImage(width, height);
        frame.drawImage(new GreenfootImage("BattleTagFrame.png"), 0, 0);
        return frame;
    }

    public GreenfootImage drawHpAndExp(){
        GreenfootImage bars = new GreenfootImage(width, height);
        // black background rectangle drawn first, which is visible behind a non-full HP bar
        bars.setColor(Color.WHITE);
        // THIS IS WHERE THE LOCATION SHOULD BE SET, THE IMAGE SIZE SHOULDN'T DETERMINE THE RECTANGLE SIZE
        bars.fillRect(169, 49, healthBarWidth, healthBarHeight);
        bars.fillRect(50, 93, expBarWidth, expBarHeight);
        bars.setColor(Color.GREEN);
        bars.fillRect(169, 49, (int)(health * pixelsPerHealthPoint), healthBarHeight);
        bars.setColor(Color.CYAN);
        bars.fillRect(50, 93, (int)(exp * pixelsPerExp), expBarHeight);
        return bars;
    }

    // this method sets the health of the health bar 
    public void setHealth(int health){
        this.health = health;
    }

    // this method gets the health of the health bar 
    public int getHealth(){
        return (int)health;
    }
}
