import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** 
 * @author Alex Do
 */
public class Defend extends StatChange
{
    private static String name = "Defend";
    private static String statType = "def";
    private Pokemon caster;
    private boolean enemy;
    
    public Defend(Pokemon caster, boolean enemy){
        super(name, statType, (int)(caster.getDefense() / 2), caster , enemy); //increase def by 1.5 
    } 
}
