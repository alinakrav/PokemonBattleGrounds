import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mudkip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mudkip extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Mudkip";
    private int level;
    private static int evolutionForm = 2;
    private static int statPreset= 2;
    private static String[] moveSet = {"Ice Shard", "Rock", "Defend", "Creeping Barrage"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 150;
    private static int height = 150;
    
    public Mudkip(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }
}
