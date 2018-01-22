import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arcanine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arcanine extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Arcanine";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 1;
    private static String[] moveSet = {"Fire Ball","Strengthen","Heal","Magic Laser"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 150;
    private static int height = 150;
    
    public Arcanine(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }  
}
