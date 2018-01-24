import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** 
 * @author Alex Do
 */
public class Golbat extends Pokemon
{
    //Stats for Pokemon
    private static String name = "Golbat";
    private int level;
    private static int evolutionForm = 1;
    private static int statPreset= 3;
    private static String[] moveSet = {"Ice Shard","Heal","Beam","Strengthen"};

    //Misc
    private boolean enemy;
    private static int width = 200;
    private static int height = 200;

    public Golbat(int level, boolean enemy){
        super(name, level, statPreset, moveSet, evolutionForm, width, height, enemy);
    }   
}
