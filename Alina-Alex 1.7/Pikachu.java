import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pikachu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pikachu extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Pikachu";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 3;
    private static String[] moveSet = {"Fire_ball", "Fire_ball", "Fire_ball", "Fire_ball"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 50;
    private static int height = 50;
    
    public Pikachu(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }
}
