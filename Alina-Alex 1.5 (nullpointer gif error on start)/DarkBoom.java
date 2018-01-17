import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DarkBoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DarkBoom extends Attack
{
    private static String name = "Dark_boom";
    private static int damage = 7;
    private static int speed = 4;
    private Pokemon attacker;
    private boolean enemy;
    private int targetX;
    private int targetY;
    public DarkBoom(Pokemon attacker, boolean enemy, int targetX, int targetY){
        super(name, damage, speed, attacker, enemy, targetX, targetY);
        
    }  
}
