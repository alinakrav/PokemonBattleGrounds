import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Alex Do
 */
public class Rock extends Attack
{
    private static String name = "Rock";
    private static int damage = 3;
    private static int speed = 5;
    private Pokemon attacker;
    private boolean enemy;
    private int targetX;
    private int targetY;
    public Rock(Pokemon attacker, boolean enemy, int targetX, int targetY){
        super(name, damage, speed, attacker, enemy, targetX, targetY);

    }     
}


