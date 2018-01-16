import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Jigglypuff here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Jigglypuff extends Pokemon
{
    //Stats for Pokemon
    private static String name = "jigglypuff";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 4;
    private static String[] moveSet = {"Fire Ball", "Fire Ball", "Fire Ball", "Fire Ball"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 50;
    private static int height = 50;
    
    public Jigglypuff(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }
    
    /**
     * Act - do whatever the Jigglypuff wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     
    public void act() 
    {
        // Add your action code here.
    }*/
}
