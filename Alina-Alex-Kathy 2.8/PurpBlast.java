import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Write a description of class PurpBlast here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PurpBlast extends Attack
{
    private static String name = "Purp Blast";
    private static int damage = 7;
    private static int speed = 3;
    private Pokemon attacker;
    private boolean enemy;
    private int targetX;
    private int targetY;
    public PurpBlast(Pokemon attacker, boolean enemy, int targetX, int targetY){
        super(name, damage, speed, attacker, enemy, targetX, targetY);
        
    }      
}
