import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gyarados here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gyarados extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Gyarados";
    private int level;
    private static int evolutionForm = 2;
    private static int statPreset= 6;
    private static String[] moveSet = {"Fire Ball", "Ice Shard", "Strengthen", "Dark Boom"};

    //Misc
    private boolean enemy;
    private static int width = 600;
    private static int height = 300;

    public Gyarados(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }
}
