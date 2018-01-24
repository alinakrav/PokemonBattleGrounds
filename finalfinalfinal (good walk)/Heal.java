import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo) 

/** 
 * @author Alex Do
 */
public class Heal extends StatChange
{
    private static String name = "Heal";
    private static String statType = "hp";
    private Pokemon caster;
    private boolean enemy;
    
    public Heal(Pokemon caster, boolean enemy){
        super(name, statType, (int)(caster.getHealth() / 4), caster , enemy); //value is the 1/4 of pokemon's current health 
    }   
}
