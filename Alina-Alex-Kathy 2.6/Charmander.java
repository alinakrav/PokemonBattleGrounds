import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Charmander here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Charmander extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Charmander";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset = 1;
    private static String[] moveSet = {"Fire_ball", "Rock", "Strengthen", "Beam"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 150;
    private static int height = 150;
    public Charmander(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }
    
}
