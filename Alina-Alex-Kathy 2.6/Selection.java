////////////////  INTERFACE ////////////////

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Selection extends Actor
{
    boolean init = true;
    World world;
    KeyReader keys;
    //////////

    String key;
    int[] gridIndex = {0, 0}; // always starts at location 0
    Actor[][] grid;

    public Selection(ArrayList<Button> objectList, boolean twoColumns, Button dummyParameter) {
        setImage("null.png");
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
        }
        else { // if only one column, then grid consists of one array, to which the arraylist is just converted
            Button[][] temp = {objectList.toArray(new Button[objectList.size()])};
            grid = temp;
            temp = null;
        }
    }

    boolean canPress; // this determines whether user can press with this selection (only for this constructor)
    public Selection(ArrayList<Item> objectList, boolean twoColumns, Item dummyParameter, boolean canPress) {
        this.canPress = canPress;
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

    PartyTag closeButton;
    public Selection(ArrayList<PartyTag> objectList, boolean twoColumns, PartyTag dummyParameter) {
        setImage("null.png");
        if(twoColumns) { // if two columns, then the arraylist needs to be split into 2 arrays first
            PartyTag[] firstColumn, secondColumn;
            secondColumn = new PartyTag[objectList.size()/2];
            firstColumn = new PartyTag[objectList.size() - secondColumn.length + 1]; // bigger half, plus one spot for the close button
            for(int i = 0; i < firstColumn.length - 1; i++) // populate first and second column lists with the buttons
                firstColumn[i] = objectList.get(i);

            // close button is indicated with 'null' pokemon. X and Y of the close button are defined here
            closeButton = new PartyTag(null, 400, 550);
            firstColumn[firstColumn.length - 1] = closeButton;

            for(int i = 0; i < secondColumn.length; i++)
                secondColumn[i] = objectList.get(firstColumn.length - 1 + i);
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
            if(keys.keyIs("down") && grid[0][0] instanceof PartyTag && gridIndex[0] > 0 && gridIndex[1] == grid[gridIndex[0]].length - 1) {
                setLocation(grid[--gridIndex[0]][grid[gridIndex[0]].length - 1].getX(), grid[gridIndex[0]][grid[gridIndex[0]].length - 1].getY());
                gridIndex[1] = grid[gridIndex[0]].length - 1;
            }
            else if(keys.keyIs("down") && gridIndex[1] < grid[gridIndex[0]].length - 1)
                setLocation(grid[gridIndex[0]][++gridIndex[1]].getX(), grid[gridIndex[0]][gridIndex[1]].getY()); 
            else if(keys.keyIs("up") && grid[0][0] instanceof PartyTag && gridIndex[0] < grid.length - 1 && gridIndex[1] == grid[gridIndex[0]].length - 1) {
                setLocation(grid[++gridIndex[0]][grid[gridIndex[0]].length - 1].getX(), grid[gridIndex[0]][grid[gridIndex[0]].length - 1].getY());
                gridIndex[1] = grid[gridIndex[0]].length - 1;
            }
            else if(keys.keyIs("up") && gridIndex[1] > 0)
                setLocation(grid[gridIndex[0]][--gridIndex[1]].getX(), grid[gridIndex[0]][gridIndex[1]].getY());
            else if(keys.keyIs("right") && gridIndex[0] < grid.length - 1 && gridIndex[1] < grid[gridIndex[0] + 1].length)
                setLocation(grid[++gridIndex[0]][gridIndex[1]].getX(), grid[gridIndex[0]][gridIndex[1]].getY());
            else if(keys.keyIs("left") && gridIndex[0] > 0 && gridIndex[1] < grid[gridIndex[0] - 1].length) 
                setLocation(grid[--gridIndex[0]][gridIndex[1]].getX(), grid[gridIndex[0]][gridIndex[1]].getY());

            hoverOverCurrent(); // let the hovered over object do whatever it does before being selected
            if(grid[0][0] instanceof Item) {
                if((canPress || (((Item)currentItem()).name).equals("Close")) && keys.keyIs("enter")) // if the selection is on bag items, it can only press according to canPress boolean
                    selectCurrent();
            }
            else if(keys.keyIs("enter")) // if selection is not for bag items, then it will always press
                selectCurrent();
        }
    }

    private void hoverOverCurrent() {
        if(currentItem() instanceof Button)
            ((Button)currentItem()).hover();
        else if(currentItem() instanceof Item)
            ((Item)currentItem()).hover();
        else if(currentItem() instanceof PartyTag)
            ((PartyTag)currentItem()).hover();
    }

    private void selectCurrent() {
        if(currentItem() instanceof Button)
            ((Button)currentItem()).select();
        else if(currentItem() instanceof Item)
            ((Item)currentItem()).select();
        else if(currentItem() instanceof PartyTag)
            ((PartyTag)currentItem()).select();
    }

    private Actor currentItem() {
        return grid[gridIndex[0]][gridIndex[1]];
    }

    public void prepare() {
        if(init) {
            if(getWorld() instanceof Battle) {
                world = (Battle)getWorld();
                keys = ((Battle)world).getKeys();
            }
            else {
                world = (ScrollingWorld)getWorld();
                keys = ((ScrollingWorld)world).getKeys();
            }
            init = false;
            /////////

            // if this selection is on a PartyTag collection, closeButton will be initialised and must be added to world
            if(closeButton != null) 
            // world.addObject(closeButton, 200,200);
                world.addObject(closeButton, closeButton.getX(), closeButton.getY());
            hoverOverCurrent();
        }
    }

    public Actor[][] getGrid() {
        return grid;
    }

    public void setGrid(Actor[][] grid) {
        this.grid = grid;
    }

}
