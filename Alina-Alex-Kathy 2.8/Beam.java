import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Beam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Beam extends Attack
{
    private static String name = "Beam";
    private static int damage = 4;
    private static int speed = 5;
    private Pokemon attacker;
    private boolean enemy;
    private int targetX;
    private int targetY;
    public Beam(Pokemon attacker, boolean enemy, int targetX, int targetY){
        super(name, damage, speed, attacker, enemy, targetX, targetY);
        
    }     
}
