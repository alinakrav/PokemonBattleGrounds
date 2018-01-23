import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is for making moves that affect the one that 
 * does them, so self-changing moves. They just need to play 
 * out an animation for the user to see they are being done 
 * 
 * @author Alex Do
 */
public class StatChange extends Move
{
    //Has to do its animation, then apply a stat change after the animation is OVER.
    //Animation occurs over the pokemon that casts it
    private String name;
    private String statType; //which stat is being affected? it can be attack, defense, speed, etc
    private int value; //value of stat change
    private Pokemon caster; //pokemon casting the stat change
    private static final int animationTime = 50 * 2; //approximately 3 seconds
    private int timer = 0; //the timer that coutns for how long the animation should go on
    public StatChange(String name, String statType, int value, Pokemon caster, boolean enemy){
        super(name, enemy);
        this.statType = statType;
        this.value = value;
        this.caster = caster;
    }

    public void act(){
        //animate for 3 seconds
        super.act();
        if(timer++ == animationTime){
            //do the stat change after the animation
            if(statType.equals("att")){
                caster.attackChange(value);
            }
            else if(statType.equals("def")){
                caster.defenseChange(value);
            }
            else if(statType.equals("hp")){
                caster.healthChange(value);
            }
            else if(statType.equals("spd")){
                caster.speedChange(value);
            }
            changeTurn();
            getWorld().removeObject(mvDescObj);
            if(enemy) 
                ((Battle)getWorld()).goToMenu();
            getWorld().removeObject(this); //remove the object animation
        }
    }
}

