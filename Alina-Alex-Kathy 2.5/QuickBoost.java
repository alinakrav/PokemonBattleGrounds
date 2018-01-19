import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class QuickBoost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QuickBoost extends StatChange
{
    private static String name = "QuickBoost";
    private static String statType = "spd";
    private Pokemon caster;
    private boolean enemy;
    
    public QuickBoost(Pokemon caster, boolean enemy){
        super(name, statType, (int)(caster.getSpeed() / 2), caster , enemy); //increase def by 1.5 
    }   
}
