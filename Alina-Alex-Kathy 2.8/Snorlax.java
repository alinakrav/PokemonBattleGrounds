import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snorlax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snorlax extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Snorlax";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 7;
    private static String[] moveSet = {"Rock","Magic Laser","Defend","Ice Shard"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 150;
    private static int height = 150;
    
    public Snorlax(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }   
}
