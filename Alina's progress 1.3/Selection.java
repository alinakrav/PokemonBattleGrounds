////////////////  INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Selection extends Actor
{
    boolean init = true;
    MyWorld world;
    KeyReader keys;
    //////////

    String key;
    int[] gridIndex = {0, 0}; // always starts at location 0
    Actor[][] grid;

    public Selection(ArrayList<Button> objectList, boolean twoColumns, Button dummyParameter) {
        if(twoColumns) { // if two columns, then the arraylist needs to be split into 2 arrays first
            Button[] firstColumn, secondColumn;
            secondColumn = new Button[objectList.size()/2];
            firstColumn = new Button[objectList.size() - secondColumn.length];
            for(int i = 0; i < firstColumn.length; i++) // populate first and second column lists with the buttons
                firstColumn[i] = objectList.get(i);
            for(int i = 0; i < secondColumn.length; i++)
                secondColumn[i] = objectList.get(firstColumn.length + i);

            Button[][] temp = {firstColumn, secondColumn};

            grid = temp;
            temp = null;
            //System.out.println("constructor: " + ((Button)grid[0][0]).name);
        }
        else { // if only one column, then grid consists of one array, to which the arraylist is just converted
            Button[][] temp = {objectList.toArray(new Button[objectList.size()])};
            grid = temp;
            temp = null;
        }
    }

    public Selection(ArrayList<Item> objectList, boolean twoColumns, Item dummyParameter) {
        setImage("itemSelection.png");
        if(twoColumns) { // if two columns, then the arraylist needs to be split into 2 arrays first
            Item[] firstColumn, secondColumn;
            secondColumn = new Item[objectList.size()/2];
            firstColumn = new Item[objectList.size() - secondColumn.length];
            for(int i = 0; i < firstColumn.length; i++) // populate first and second column lists with the buttons
                firstColumn[i] = objectList.get(i);
            for(int i = 0; i < secondColumn.length; i++)
                secondColumn[i] = objectList.get(firstColumn.length + i);
            Item[][] temp = {firstColumn, secondColumn};
            grid = temp;
            temp = null;
        }
        else { // if only one column, then grid consists of one array, to which the arraylist is just converted
            Item[][] temp = {objectList.toArray(new Item[objectList.size()])};
            grid = temp;
            temp = null;
        }
    }

    public Selection(ArrayList<PartyTag> objectList, boolean twoColumns, PartyTag dummyParameter) {
        setImage("itemSelection.png");
        if(twoColumns) { // if two columns, then the arraylist needs to be split into 2 arrays first
            PartyTag[] firstColumn, secondColumn;
            secondColumn = new PartyTag[objectList.size()/2];
            firstColumn = new PartyTag[objectList.size() - secondColumn.length];
            for(int i = 0; i < firstColumn.length; i++) // populate first and second column lists with the buttons
                firstColumn[i] = objectList.get(i);
            for(int i = 0; i < secondColumn.length; i++)
                secondColumn[i] = objectList.get(firstColumn.length + i);
            PartyTag[][] temp = {firstColumn, secondColumn};
            grid = temp;
            temp = null;
        }
        else { // if only one column, then grid consists of one array, to which the arraylist is just converted
            PartyTag[][] temp = {objectList.toArray(new PartyTag[objectList.size()])};
            grid = temp;
            temp = null;
        }
    }

    public void act() 
    {
        prepare();

        if(keys.keyNotNull()) {
            // see if second coordinate (y) is less than max index of current x coord-th array length in grid
            if(keys.keyIs("down") && gridIndex[1] < grid[gridIndex[0]].length - 1) 
                setLocation(grid[gridIndex[0]][++gridIndex[1]].getX(), grid[gridIndex[0]][gridIndex[1]].getY()); 
            else if(keys.keyIs("up") && gridIndex[1] > 0)
                setLocation(grid[gridIndex[0]][--gridIndex[1]].getX(), grid[gridIndex[0]][gridIndex[1]].getY());
            else if(keys.keyIs("right") && gridIndex[0] < grid.length - 1)
                setLocation(grid[++gridIndex[0]][gridIndex[1]].getX(), grid[gridIndex[0]][gridIndex[1]].getY());
            else if(keys.keyIs("left") && gridIndex[0] > 0) 
                setLocation(grid[--gridIndex[0]][gridIndex[1]].getX(), grid[gridIndex[0]][gridIndex[1]].getY());

            hoverOverCurrent(); // let the hovered over object do whatever it does before being selected
            if(keys.keyIs("enter"))
                selectCurrent();
        }
    }

    private void hoverOverCurrent() {
        if(currentItem() instanceof Button)
            ((Button)currentItem()).whenHovered();
        else if(currentItem() instanceof Item)
            ((Item)currentItem()).whenHovered();
    }

    private void selectCurrent() {
        if(currentItem() instanceof Button)
            ((Button)currentItem()).select();
        else if(currentItem() instanceof Item)
            ((Item)currentItem()).select();
    }

    private Actor currentItem() {
        return grid[gridIndex[0]][gridIndex[1]];
    }

    public void prepare() {
        if(init) {
            world = (MyWorld)getWorld();
            keys = world.getKeys();
            init = false;
            /////////
            hoverOverCurrent();
        }
    }
}
