import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Attack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Attack extends Move
{
    boolean attackDone = false; // makes sure attack is only applying its effects once, before the animation plays out
    int cooldownCount = 0;
    int cooldown = 50;

    Pokemon pokemon; // the pokemon the attack is supposed to reach
    private String name;
    private int damage;
    private int speed;
    Pokemon attacker;
    private int targetX;
    private int targetY;
    public Attack(String name, int damage, int speed, Pokemon attacker, boolean enemy, int targetX, int targetY){
        super(name, enemy);
        this.name = name;
        this.damage = damage;
        this.speed = speed;
        this.attacker = attacker;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public Attack(String name, int speed, Pokemon attacker, int targetX, int targetY){
        super(name, false);
        this.name = name;
        this.speed = speed;
        this.attacker = attacker;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    /**
     * Act - do whatever the Attack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        if(!attackDone) { // don't move if attack 
            move(speed);
            turnTowards(targetX, targetY);
        }
        //keep it in domain

        if(getX() > getWorld().getWidth()-5 || getX() < 5 || getY() > getWorld().getHeight() - 5) {
            getWorld().removeObject(this);
        }
        else 
            hit();
    }  

    public void hit() {
        pokemon = (Pokemon)getOneObjectAtOffset(0,0, Pokemon.class);

        if(this instanceof Pokeball && pokemon != null && pokemon != attacker) {
            attackDone = true;
            if((((Battle)getWorld()).getParty()).size() < 6) 
                capture();
            else
                removePokeball();
        }
        else {
            // if the non-attacking pokemon was hit (pokemon != null)
            if((pokemon != null && pokemon != attacker)) {
                // this part does actual damage to the opponent
                int damageInflicted = 0; // default (before calculated, initialised for scope visibility outside if statement
                if(!attackDone) {// only do the following if attack hasn't applied its stat changes yet
                    // the attack of the attacker (charmander) is way too high, by itself it reads as 37 at the moment
                    damageInflicted = 1; //(int)(attacker.getAttack() * (damage * 0.1 + 1)); // calculate stat effect of attack
                    if(pokemon.getCurHealth() <= 0) 
                        attacker.expToLevelUpChange(-pokemon.getExp()); // give attacker exp of opponent if it killed the opponent
                }
                pokemon.getHit(damageInflicted, attackDone); // apply stat effect of attack
                attackDone = true; // do not inflict damage (and other things besides cooldown actions) for the next acts
                // this is where the before and after of the cooldown is controlled (image cleared before explosion, and everything else is done afterwardsa) 
                if(cooldownCount++ < cooldown) {
                    getImage().clear(); // remove the image every act after the explosion is created (because each act a new frame is shown from the gif)
                }
                else { // when cooldown (for animation) is over
                    attackDone = false; // reset the attack status for next attack
                    changeTurn(); // prepare turn for next actions
                    getWorld().removeObject(mvDescObj);
                    if(enemy) {
                        ((Battle)getWorld()).goToMenu();
                    }
                    getWorld().removeObject(this);
                }
            }
        }
    }

    public void capture() {
        pokemon.addToParty();
        pokemon.removeBattleTag(); // remove the tag displaying its stats in battle
        getWorld().removeObject(pokemon);
        // generate new enemy here or reset game
        // if capturing gives you the turn again
        ((Battle)getWorld()).goToMenu();
        changeTurn(); // prepare turn for next actions
        getWorld().removeObject(this);
        // trace here to see if statement reachable
    }

    public void removePokeball() {
        if(cooldownCount++ < cooldown) {
            if(2 < cooldownCount % 10 && cooldownCount % 10 < 10)
                setImage("null.png");
        }
        else { // end process
            attackDone = false; // reset attack status to move with the next object
            changeTurn(); // prepare turn for next actions
            getWorld().removeObject(this);
        }
    }

    public int getDamage(){
        return damage;
    }

    public int getSpeed(){
        return speed;
    }
}
