import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** 
 * @author Alex Do
 */
public class Pikachu extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Pikachu";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 3;
    private static String[] moveSet = {"Lightning Bolt", "Rock", "Quick Boost", "Beam"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 150;
    private static int height = 150;
    
    public Pikachu(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }
}
