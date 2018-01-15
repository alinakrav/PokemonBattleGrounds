import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PartyTag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PartyTag extends Actor
{
    boolean init = true; // variable to allow you to initialise some actions
    MyWorld world; // this will keep track of the world (must be declared as instance variable)
    ///////////
    int x, y;
    public String name;

    public void act() 
    {
        prepare();
    }    

    public void whenHovered() {
        for(PartyTag tag : world.getObjects(PartyTag.class)) {
            if(tag.name != null) 
                tag.setImage(tag.name + "Tag.png");
        }
        setImage(name + "TagHover.png");
    }

    // this method is called when the button is clicked. It is defined in the subclasses 
    // for functionality that is specific to that button type.
    public void select() {}

    // this method initialises world variable, and then does whatever needs to be done on instantiation 
    public void prepare() {
        if(init) {
            world = (MyWorld)getWorld();
            init = false;
            //////////
        }
    }
}
