import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pokemon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pokemon extends Actor
{
    boolean init; // for doing things on initialisation

    //Stats for Pokemon
    private String name;
    private int level;
    private int health; //total health pokemon may have
    private int curHealth; //current health pokemon has
    private int attack;
    private int defense;
    private int speed;
    private int evolutionForm;
    private int statPreset;
    private int exp; //experience that can trigger a pokemon to level up
    private int expToLevelUp; //amount pokemon needs to level up
    private int damage;

    //attacks moves for Pokemon
    private String[] moveSet; 

    //Bouncing Movement
    int bounceTimer = 0; // counts the time until the next bounce movement
    private final int bounceInterval = 20; // how much time there is between bounce movements
    int bounceX; // how many pixels character moves horizontally
    int bounceY; // how many pixels character moves vertically

    //Misc
    boolean enemy;
    boolean started; // whether the character has acted yet
    boolean turn; //whether it is this particular Pokemon's time to attack. In a battle, it will either be the Player's pokemon or the Enemy's pokemon turn to attack at a particular time.
    public static boolean attacked; // whether the character attacked yet (or else it'll spam the attacks if it's not counted)
    int deathCounter; // counts frames until death
    private int width;
    private int height;
    private int timerAmount = 100; //two second timer
    private int timer = timerAmount;

    //Variables determining where the pokemon's attack will go to
    private int targetX;
    private int targetY;
    //NameTag tag; //name tag of Pokemon
    GifImage image;
    PartyTag tag; //name tag of Pokemon

    /**
     * The Pokemon constructor gives the child object: name, stats, width, height, x and y coordinates 
     * of name tag, and vertical and horizontal bounce. It also sets the image for the Pokemon and rescales it.
     */
    public Pokemon(String name, int level, int statPreset, String[] moveSet, int evolutionForm, int width, int height, boolean enemy){ //int health, int attack, int defense, int speed, 
        this.name = name;
        this.level = level;
        this.statPreset = statPreset;
        this.moveSet = moveSet;
        this.evolutionForm = evolutionForm;
        this.width = width;
        this.height = height;
        this.enemy = enemy;
        expToLevelUp = (int)Math.pow(level, 1.5);

        statCalculation(); //sets pokemon's stats based on their statPreset and their level
        curHealth = health;
        if(enemy){
            bounceX = 0;
            bounceY = 10;
            battleView();
            targetX = 240;
            targetY = 420;
        } else { //Player pokemon
            bounceX = 15;
            bounceY = 15;
            battleView();
            targetX = 600;
            targetY = 230;
        }
    }

    /**
     * Act - do whatever the Pokemon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        enemyMove();
        setImage(image.getCurrentImage());
        levelUp();
        die(); //die if it has no health
    }  

    // this method removes the character from the world and goes to the end screen when the health is
    // depleted and a short break time is passed
    public void die() {
        if(curHealth <= 0) { // when no more health
            deathCounter++; // count frames until death           
            if(deathCounter == 80) { // after 80 frames             
                getWorld().removeObject(this); // delete character from the world
            }
        }
    }

    public void levelUp(){
        if(expToLevelUp <= 0){
            level++;
            calculateExpToLevelUp(level);       
            statCalculation();
            curHealth += 2;
        }
    }

    public void calculateExpToLevelUp(int level){
        expToLevelUp = (int)Math.pow(level, 1.5);
    }

    public void statCalculation(){ //calculate stats for each Pokemon using an equation. There will be presets for how the stats will be determined (attack preset, defense preset, speed preset, health preset, etc)
        //Preset 1: Attack
        if(statPreset == 1){
            health = 15 + level + 2 * level;
            attack = 10 + 3 * level;
            defense = 10 + 1 * level;
            speed = 10 + 1 * level;
        }
        //Preset 2: Defense
        else if(statPreset == 2){
            health = 15 + level + 2 * level;
            attack = 10 + 1 * level;
            defense = 10 + 3 * level;
            speed = 10 + 1 * level;
        }
        //Preset 3: Speed
        else if(statPreset == 3){
            health = 15 + level + 2 * level;
            attack = 10 + 1 * level;
            defense = 10 + 1 * level;
            speed = 10 + 3 * level;
        }
        //Preset 4: Health
        else if(statPreset == 4){
            health = 15 + level + 4 * level;
            attack = 10 + 1 * level;
            defense = 10 + 1 * level;
            speed = 10 + 1 * level;
        }
        //Preset 5: Balanced
        else if(statPreset == 5){
            health = 15 + level + 2 * level;
            attack = 10 + 2 * level;
            defense = 10 + 2 * level;
            speed = 10 + 2 * level;
        }
        //Preset 6: Strong (Higher Attack and Speed)
        else if(statPreset == 6){
            health = 15 + level + 2 * level;
            attack = 10 + (int)(2.5 * (double)level);
            defense = 10 + 1 * level;
            speed = 10 + (int)(2.5 * (double)level);
        }
        //Preset 7: Bulky (Higher Defense and Health)
        else if(statPreset == 7){
            health = 15 + level + 3 * level;
            attack = 10 + 2 * level;
            defense = 10 + (int)(2.5 * (double)level);
            speed = 10 + 2 * level;
        }
    }

    /*
     * Allows pokemon to evolve to it's next stage
     * Will change its picture
     * Will change its stats (add to every stat by 5)
     */
    public void evolution(){
        if(evolutionForm == 1){
            health += 5;
            curHealth += 5;
            attack += 5;
            defense += 5;
            speed += 5;

            evolutionForm++;
        }
        if(evolutionForm == 2){
            health += 5;
            curHealth += 5;
            attack += 5;
            defense += 5;
            speed += 5;

            evolutionForm++;
        }
    }

    /**
     * calculate how much the pokemon gets hurt when it is hit by an attack, 
     * using the the damage of the attack, 
     * and the defense of the pokemon getting hit. Then apply the change to 
     * the current health of the pokemon getting hit */
    public void getHit(int damageReceived){
        getWorld().addObject(new Explosion(), this.getX(), this.getY()); //add explosion 
        int damageInflicted = damageReceived - defense;
        if(damageInflicted <= 0 && curHealth - 2 >= 0){
            curHealth -= 2;
        } else if (damageInflicted <= 0){
            curHealth--;
        } else if ((curHealth - damageInflicted) >= 0){
            curHealth -= damageInflicted;
        } else {
            curHealth = 0;
        }
    }

    public void expToLevelUpChange(int change){
        expToLevelUp += change;
    }

    //RETURN STATS
    public int getHealth(){
        return health;
    }

    public int getCurHealth(){
        return curHealth;
    }

    public int getAttack(){
        return attack;
    }

    public int getDefense(){
        return defense;
    }

    public int getSpeed(){
        return speed;
    }

    public int getExp(){
        return exp;
    }

    public int getExpToLevelUp(){
        return expToLevelUp;
    }

    public int getLevel(){
        return level;
    }

    public String getName(){
        return name;
    }

    public String[] getMoveSet(){
        return moveSet;
    }

    public int getEvolutionForm(){
        return evolutionForm;
    }

    //STATS CHANGES WITHIN FOLLOWING BLOCK
    //this method lowers the health of the character by a set amount of damage
    public void lowerHealth(int damageReceived){
        curHealth -= damageReceived;
        if(curHealth < 0) //health cannot be less than 0
            curHealth = 0;    
        //tag.setHealth(health); // update character's health on their name tag
    }
    //Change Health Stat by a certain amount (use negative value to subtract, positive value to add)
    public void healthChange(int change){ 
        //health += change;
        while(curHealth <= health && change > 0){ //this way the current health cannot go over the maximum health
            curHealth++;
            change--;
        }
    }
    //Change Attack Stat by a certain amount (use negative value to subtract, positive value to add)
    public void attackChange(int change){ 
        attack += change;
    }
    //Change Defense Stat by a certain amount (use negative value to subtract, positive value to add)
    public void defenseChange(int change){ 
        defense += change;
    }
    //Change Speed Stat by a certain amount (use negative value to subtract, positive value to add)
    public void speedChange(int change){ 
        speed += change;
    }

    // this method times intervals between a character's small movements around their original position
    public void bounce() {
        bounceTimer++; // count frames
        if(bounceTimer == bounceInterval) { // when frame count reaches required interval between movements
            bounceX *= -1; // make the next movements in opposite directions of current ones
            bounceY *= -1;
            setLocation(getX() + bounceX, getY() + bounceY); // add those movements to current position (can be pos or neg)
            bounceTimer = 0; //reset interval timer
        }
    }

    public void enemyMove(){
        if(enemy && getWorld() instanceof Battle){
            if(((Battle)getWorld()).getTurn() == 1){
                int min = 0;
                int max = 3; //inclusive
                int n = (int)(min + (Math.random() *( max + 0 + 1))); //generates a random number from 0 to 3 inclusive

                move(moveSet[n]);
                ((Battle)getWorld()).setTurn(0);
            }
        }
    }

    //Pokemon will create a move object. Depending on whether it is an enemy or not it, the direction of the move will be different (Enemy attacks go toward the player's pokemon, player attacks go toward the enemy's Pokemon).
    public void move(String moveName){
        Move move1 = null;
        if(moveName.equals("Fire_ball")){
            move1 = new FireBall(this, enemy, targetX, targetY);
        }
        else if(moveName.equals("Ice_shard")){
            move1 = new IceShard(this, enemy, targetX, targetY);
        }
        else if(moveName.equals("Lightning_bolt")){
            move1 = new LightningBolt(this, enemy, targetX, targetY);
        }
        else if(moveName.equals("Celestial_spiral")){
            move1 = new CelestialSpiral(this, enemy, targetX, targetY);
        }
        else if(moveName.equals("Dark_boom")){
            move1 = new DarkBoom(this, enemy, targetX, targetY);
        }
        else if(moveName.equals("Creeping_barrage")){
            move1 = new CreepingBarrage(this, enemy, targetX, targetY);
        }
        else if(moveName.equals("Heal")){
            move1 = new Heal(this, enemy);
        }
        else if(moveName.equals("Strengthen")){
            move1 = new Strengthen(this, enemy);
        }
        else if(moveName.equals("Rock")){
            move1 = new Rock(this, enemy, targetX, targetY);
        }
        else if(moveName.equals("Beam")){
            move1 = new Beam(this, enemy, targetX, targetY);
        }
        else if(moveName.equals("Defend")){
            move1 = new Defend(this, enemy);
        }   
        else if(moveName.equals("Quick_boost")){
            move1 = new QuickBoost(this, enemy);
        }   
        else if(moveName.equals("Purp_blast")){
            move1 = new PurpBlast(this, enemy,targetX,targetY);
        }   
        else if(moveName.equals("Magic_laser")){
            move1 = new MagicLaser(this, enemy,targetX,targetY);
        }  
        else if(moveName.equals("Geometry")){
            move1 = new Geometry(this, enemy,targetX,targetY);
        }   
        getWorld().addObject(move1, this.getX(), this.getY());

        move1 = null; // fixes infrequent error from happening

    }

    //Change the pokemon's orientation. If it is facing you, then it will face the other way and vice versa.
    /**public void turn(){
    String fileName = image.getFile();
    if(fileName.contains("string")){
    image 
    }
    }*/

    /*
     * List of Pokemon
     * 
     * Jigglypuff
     * Pikachu
     * Charmander
     * Dragonite
     * Gyarados
     * Squirtle
     * Geodude
     */

    //Pokemon will create a move object. Depending on whether it is an enemy or not it, the direction of the move will be different (Enemy attacks go toward the player's pokemon, player attacks go toward the enemy's Pokemon).
    public void useMove(String moveName, boolean enemy){
        System.out.println("attack" + moveName + " used.");
        // this is where the attack is made based on the name and direction (from enemy/player)
    }

    public void useItem(String itemName, boolean enemy) {
        System.out.println(itemName + " used.");
        // this is where the item is used to boost whatever stats
    }

    // this method sets the image to a small, front-facing view of the pokemon, and sets it location to be on the PartyTag that calls this method
    public void tagView(int width, int x, int y) {
        image = new GifImage(name + ".gif");
        image.resizeImages(width, (int)((1.0*width)/getImage().getWidth()*getImage().getHeight()));
        setLocation(x, y);
    }

    public void battleView() {
        if(enemy) {
            image = new GifImage(name + ".gif");
            image.resizeImages(width, height);
            setLocation(600, 230);
        }
        else {
            image = new GifImage("Back" + name + ".gif");
            image.resizeImages(width, height);
            setLocation(240, 420);
        }
    }

    public GifImage getAnimation() {
        return image;
    }

    public void setTag(PartyTag tag) {
        this.tag = tag;
    }

}
