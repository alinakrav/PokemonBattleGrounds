import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FightButton extends Button
{
    public int x = q2X;
    public int y = q2Y;

    // constructor makes a button for a specific mode and assigns the main game speed
    public FightButton() {
        super(); // get functionality of superclass
        //setImage(new GreenfootImage("AttackButton.png")); // set image to the button appropriate to the mode
        //getImage().scale(400, 250);
        name = "fight";
    }   

    public void act() {
        super.act();
    }

    // this method is executed in the parent's act method
    public void select() {
        // make 4 attack buttons for different moves (depends on pokemon?)
        Pokemon player = Turns.player; // get current player object
        world.addObject(new FightSubButton(player.getMoveSet()[0]), q1X, q1Y);
        world.addObject(new FightSubButton(player.getMoveSet()[1]), q2X, q2Y);
        world.addObject(new FightSubButton(player.getMoveSet()[2]), q3X, q3Y);
        world.addObject(new FightSubButton(player.getMoveSet()[3]), q4X, q4Y);

        // remove all other buttons
        world.removeObjects(world.getObjects(RunButton.class)); 
        world.removeObjects(world.getObjects(BagButton.class)); 
        world.removeObjects(world.getObjects(PokemonButton.class));               
        world.removeObjects(world.getObjects(Selection.class)); 
        world.removeObjects(world.getObjects(FightButton.class)); // have to remove this button LAST

    }
}
