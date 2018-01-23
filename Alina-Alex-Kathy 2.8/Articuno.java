import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** 
 * @author Alex Do
 */
public class Articuno extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Articuno";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 2;
    private static String[] moveSet = {"Celestial Spiral","Heal","Ice Shard","Beam"};

    //Misc
    private boolean enemy;
    private static int width = 250;
    private static int height = 250;

    public Articuno(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }
}
