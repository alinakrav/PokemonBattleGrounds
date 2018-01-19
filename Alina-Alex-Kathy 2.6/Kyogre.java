import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Kyogre here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kyogre extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Kyogre";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 7;
    private static String[] moveSet = {"Defend","Ice_shard","Dark_boom","Purp_blast"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 150;
    private static int height = 150;
    
    public Kyogre(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }  
}
