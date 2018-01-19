import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Golbat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Golbat extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Golbat";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 3;
    private static String[] moveSet = {"Ice_shard","Heal","Beam","Strengthen"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 150;
    private static int height = 150;
    
    public Golbat(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }   
}
