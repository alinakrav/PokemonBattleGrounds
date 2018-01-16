import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class FightButton extends Button
{
    Selection selection;

    // constructor makes a button for a specific mode and assigns the main game speed
    public FightButton() {
        super(); // get functionality of superclass
        //setImage(new GreenfootImage("AttackButton.png")); // set image to the button appropriate to the mode
        //getImage().scale(400, 250);
        x = q1X;
        y = q1Y;
        name = "fight";
        setImage(new GreenfootImage(name + "Button.png"));
    }   

    public void act() {
        super.act();
    }

    // this method is executed in the parent's act method
    public void select() {
        Pokemon player = Turns.player; // get current player object

        // remove all other buttons
        world.removeObjects(world.getObjects(Selection.class)); 
        world.removeObjects(world.getObjects(Button.class)); 

        ///// this needs to be added to world before this button is deleted     
        ArrayList<Button> moveButtons = new ArrayList<>();
        for(int i = 0; i < 4; i++) {        
            moveButtons.add(new FightSubButton(player.getMoveSet()[i]));
            world.addObject(moveButtons.get(i), quadrants[i][0], quadrants[i][1]);
        }

        selection = new Selection(moveButtons, true, moveButtons.get(0));
        world.addObject(selection, moveButtons.get(0).x, moveButtons.get(0).y);
    }
}
