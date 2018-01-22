import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tropius here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tropius extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Tropius";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 4;
    private static String[] moveSet = {"Defend","Rock","Celestial Spiral","Purp Blast"};

    //Misc
    private boolean enemy;
    private static int width = 300;
    private static int height = 300;

    public Tropius(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }   
}
