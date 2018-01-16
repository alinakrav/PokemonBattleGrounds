import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CreepingBarrage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CreepingBarrage extends Attack
{
    private static String name = "Creeping Barrage";
    private static int damage = 7;
    private static int speed = 5;
    private Pokemon attacker;
    private boolean enemy;
    private int targetX;
    private int targetY;
    public CreepingBarrage(Pokemon attacker, boolean enemy, int targetX, int targetY){
        super(name, damage, speed, attacker, enemy, targetX, targetY);
        
    }     
}
