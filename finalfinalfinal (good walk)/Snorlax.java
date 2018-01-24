import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** 
 * @author Alex Do
 */
public class Snorlax extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Snorlax";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 7;
    private static String[] moveSet = {"Rock","Magic Laser","Defend","Ice Shard"};

    //Misc
    private boolean enemy;
    private static int width = 350;
    private static int height = 350;

    public Snorlax(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }   
}
