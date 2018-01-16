//////////////// BAG INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class MoveSelection extends Actor
{
    boolean init = true;
    MyWorld world;
    KeyReader keys;
    //////////

    String key;
    int[] gridIndex = {0, 0}; // always starts at location 0
    ArrayList<Button> buttonsList;
    Button[][] buttonGrid;

    public MoveSelection(ArrayList<Button> buttonList) {
        this.buttonsList = buttonsList;

        Button[] firstColumn, secondColumn;
        secondColumn = new Button[buttonList.size()/2];
        firstColumn = new Button[buttonList.size() - secondColumn.length];
        for(int i = 0; i < firstColumn.length; i++) // populate first and second column lists with the buttons
            firstColumn[i] = buttonList.get(i);
        for(int i = 0; i < secondColumn.length; i++)
            secondColumn[i] = buttonList.get(firstColumn.length + i);
        Button[][] temp = {firstColumn, secondColumn};
        buttonGrid = temp;
        temp = null;
    }

    public void act() 
    {
        prepare();

        if(keys.keyNotNull()) {
            // see if second coordinate (y) is less than max index of current x coord-th array length in buttonGrid
            if(keys.keyIs("down") && gridIndex[1] < buttonGrid[gridIndex[0]].length - 1) 
                setLocation(buttonGrid[gridIndex[0]][++gridIndex[1]].getX(), buttonGrid[gridIndex[0]][gridIndex[1]].getY()); 
            else if(keys.keyIs("up") && gridIndex[1] > 0)
                setLocation(buttonGrid[gridIndex[0]][--gridIndex[1]].getX(), buttonGrid[gridIndex[0]][gridIndex[1]].getY());
            else if(keys.keyIs("right") && gridIndex[0] < buttonGrid.length - 1)
                setLocation(buttonGrid[++gridIndex[0]][gridIndex[1]].getX(), buttonGrid[gridIndex[0]][gridIndex[1]].getY());
            else if(keys.keyIs("left") && gridIndex[0] > 0) 
                setLocation(buttonGrid[--gridIndex[0]][gridIndex[1]].getX(), buttonGrid[gridIndex[0]][gridIndex[1]].getY());

            //if(keys.keyIs("enter"))
            //buttonList.select(currentItem());
        }
    }

    private Item currentItem() {
        return world.getObjectsAt(getX(), getY(), Item.class).get(0);
    }

    public void prepare() {
        if(init) {
            world = (MyWorld)getWorld();
            keys = world.getKeys();
            init = false;
            /////////
        }
    }
}
