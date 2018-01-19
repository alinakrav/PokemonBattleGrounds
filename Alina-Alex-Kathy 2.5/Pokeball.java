import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pokeball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pokeball extends Attack
{
    private static String name = "Pokeball";
    private static int speed = 5;
    private Pokemon attacker;
    private boolean enemy;
    private int targetX;
    private int targetY;
    public Pokeball(int targetX, int targetY){
        super(name, speed, targetX, targetY);
        
    }     
}
