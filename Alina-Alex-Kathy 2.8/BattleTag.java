import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

/**
 * This class is for making the HP/stats bar during a Battle (not party view).
 * It has exp, hp, and level on the tag, as well as name of the pokemon currently 
 * battling. It is a simple class because it only has the function of 
 * getting a pokemon's info every act, and then redrawing itself based on that.
 * 
 * @author Alina Kravchenko
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
    // get all the relevant info about this pokemon at first
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
        // update relevant info here 
        level = pokemon.getLevel();
        health = pokemon.getCurHealth();
        exp = maxExp - pokemon.getExpToLevelUp();
        // update the tag itself here
        update();
    }

    public void init() {
        if(init) {
            init = false;
            if(enemy)
                setLocation(150, 165);
            else
                setLocation(650, 390);
        }
    }

    public void update() {
        GreenfootImage tag = new GreenfootImage(width, height);
        tag.drawImage(drawHpAndExp(), 0, 0);
        tag.drawImage(drawFrame(), 0, 0);
        tag.drawImage(drawStats(), 0, 0);
        setImage(tag);
    }

    public GreenfootImage drawStats() { 
        GreenfootImage stats = new GreenfootImage(width, height);
        stats.setColor(Color.BLACK);
        // draw the name (for charmander's long name, make font smaller)
        if(enemy) {
            if(!name.equals("Charmander")) {
                stats.setFont(new java.awt.Font("Dialog", Font.PLAIN, 35));
                stats.drawString(name, 10, 40);
            }
            else {
                stats.setFont(new java.awt.Font("Dialog", Font.PLAIN, 30));
                stats.drawString(name, 10, 40);
            }
            stats.setFont(new java.awt.Font("Dialog", Font.PLAIN, 25));
            stats.drawString(("Lv." + level), 194, 38);
            stats.drawString((health + "  " + maxHealth), 155, 85);
        }
        else {
            if(!name.equals("Charmander")) {
                stats.setFont(new java.awt.Font("Dialog", Font.PLAIN, 35));
                stats.drawString(name, 45, 40);
            }
            else {
                stats.setFont(new java.awt.Font("Dialog", Font.PLAIN, 30));
                stats.drawString(name, 45, 40);
            }
            stats.setFont(new java.awt.Font("Dialog", Font.PLAIN, 25));
            stats.drawString(("Lv." + pokemon.getLevel()), 235, 38);
            stats.drawString((health + "  " + maxHealth), 198, 85);
        }

        return stats;
    }

    public GreenfootImage drawFrame() {
        GreenfootImage frame = new GreenfootImage(width, height);
        if(!enemy)
            frame.drawImage(new GreenfootImage("BattleTagFramePlayer.png"), 0, 0);
        else
            frame.drawImage(new GreenfootImage("BattleTagFrameEnemy.png"), 0, 0);
        return frame;
    }

    public GreenfootImage drawHpAndExp(){
        GreenfootImage bars = new GreenfootImage(width, height);
        // black background rectangle drawn first, which is visible behind a non-full HP bar
        bars.setColor(Color.WHITE);
        // THIS IS WHERE THE LOCATION SHOULD BE SET, THE IMAGE SIZE SHOULDN'T DETERMINE THE RECTANGLE SIZE
        if(!enemy) {
            bars.fillRect(169, 49, healthBarWidth, healthBarHeight);
            bars.fillRect(50, 93, expBarWidth, expBarHeight);
            bars.setColor(Color.GREEN);
            bars.fillRect(169, 49, (int)(health * pixelsPerHealthPoint), healthBarHeight);
            bars.setColor(Color.CYAN);
            bars.fillRect(50, 93, (int)(exp * pixelsPerExp), expBarHeight);
        }
        else { // different locations of the bars
            bars.fillRect(128, 49, healthBarWidth, healthBarHeight);
            bars.fillRect(9, 93, expBarWidth, expBarHeight);
            bars.setColor(Color.GREEN);
            bars.fillRect(128, 49, (int)(health * pixelsPerHealthPoint), healthBarHeight);
            bars.setColor(Color.CYAN);
            bars.fillRect(9, 93, (int)(exp * pixelsPerExp), expBarHeight);
        }
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
