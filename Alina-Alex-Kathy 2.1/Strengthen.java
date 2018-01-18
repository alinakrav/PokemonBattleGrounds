import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Strengthen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Strengthen extends StatChange
{
    private static String name = "Strengthen";
    private static String statType = "att";
    private Pokemon caster;
    private boolean enemy;
    
    public Strengthen(Pokemon caster, boolean enemy){
        super(name, statType, (int)(caster.getAttack() / 2), caster , enemy); //increase attack by 1.5 
    } 
}
