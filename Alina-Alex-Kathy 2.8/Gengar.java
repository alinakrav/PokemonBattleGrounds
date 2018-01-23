import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** 
 * @author Alex Do
 */
public class Gengar extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Gengar";
    private int level;
    private static int evolutionForm = 3;
    private static int statPreset= 1;
    private static String[] moveSet = {"Dark Boom","Creeping Barrage","Fire Ball","Quick Boost"};
    
    
    //Misc
    private boolean enemy;
    private static int width = 150;
    private static int height = 150;
    
    public Gengar(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    } 
}
