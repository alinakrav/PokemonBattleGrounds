import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Geometry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Geometry extends Attack
{
    private static String name = "Geometry";
    private static int damage = 10;
    private static int speed = 6;
    private Pokemon attacker;
    private boolean enemy;
    private int targetX;
    private int targetY;
    public Geometry(Pokemon attacker, boolean enemy, int targetX, int targetY){
        super(name, damage, speed, attacker, enemy, targetX, targetY);
        
    }        
}
