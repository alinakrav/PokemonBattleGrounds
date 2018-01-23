import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * This class is the 'fight' button, which simply leads the 
 * user to an extended menu of all the moves he is able to use with 
 * the current pokemon. 
 * 
 * When the current player pokemon faints, this button, along with the 
 * bag button, is unavailable and cannot be pressed.
 * 
 *  @author Alina Kravchenko
 */
public class FightButton extends Button
{
    Selection selection;
    boolean greyOut;

    // constructor makes a button for a specific mode and assigns the main game speed
    public FightButton() {
        super(); // get functionality of superclass
        x = q1X;
        y = q1Y;
        name = "Fight";
        setImage(new GreenfootImage("Button" + name + ".png"));
    }   

    public void act() {
        super.act();
        greyOutIfDead();
    }

    // this method is executed in the parent's act method
    public void select() {
        Pokemon player = world.player; // get current player object

        // remove all other buttons
        world.removeObjects(world.getObjects(Selection.class)); 
        world.removeObjects(world.getObjects(Button.class)); 

        ///// this needs to be added to world before this button is deleted     
        ArrayList<Button> moveButtons = new ArrayList<Button>();

        for(int i = 0; i < 4; i++) {        
            moveButtons.add(new MoveButton(player.getMoveSet()[i]));
            world.addObject(moveButtons.get(i), quadrants[i][0], quadrants[i][1]);
        }

        selection = new Selection(moveButtons, true, moveButtons.get(0));
        world.addObject(selection, moveButtons.get(0).x, moveButtons.get(0).y);
    }

    // this limits the top 2 buttons unpressable if the player is dead, and makes them available again after 
    public void greyOutIfDead() {
        if(world.player.getWorld() != null) { // if player exists
            if(this.getImage().toString().equals("Button" + name + "Grey.png")) { // if this was greyed out before
                greyOut = false; // now it shouldn't be greyed out

                this.setImage("Button" + name + ".png"); // set to normal mode
                getWorld().getObjects(BagButton.class).get(0).setImage("ButtonBag.png"); // also set the bag button to normal 
            }
        }
        else if (world.getParty().isEmpty()) { // if no one left in party, then you lose the game
            Greenfoot.setWorld(new ScrollingWorld(((Battle)getWorld()).beatenTrainers, ((Battle)getWorld()).x, ((Battle)getWorld()).y, ((Battle)getWorld()).bag, ((Battle)getWorld()).party));
        }
        else if(world.player.getWorld() == null && !greyOut) { // if they player is not currently in world (dead)
            // grey the top buttons out 
            this.setImage("Button" + name + "Grey.png");
            getWorld().getObjects(BagButton.class).get(0).setImage("ButtonBagGrey.png");
            Actor[][] grid = getWorld().getObjects(Selection.class).get(0).getGrid();
            Actor[][] tempGrid = new Actor[grid.length][grid[0].length - 1];
            grid[0][0] = grid[0][1]; // the first button is now the second one from the top
            grid[1][0] = grid[1][1];
            getWorld().getObjects(Selection.class).get(0).setGrid(grid);
            greyOut = true;
        }
    }
}
