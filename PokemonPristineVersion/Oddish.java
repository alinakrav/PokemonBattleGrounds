import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** 
 * @author Alex Do
 */
public class Oddish extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Oddish";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 5;
    private static String[] moveSet = {"Rock","Defend","Purp Blast","Heal"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 150;
    private static int height = 150;
    
    public Oddish(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }  
}
