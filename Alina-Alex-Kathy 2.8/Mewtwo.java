import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mewtwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mewtwo extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Mewtwo";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 6;
    private static String[] moveSet = {"Fire Ball","Lightning Bolt","Geometry","Beam"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 300;
    private static int height = 300;
    
    public Mewtwo(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }  
}
