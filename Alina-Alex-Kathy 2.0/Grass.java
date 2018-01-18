import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;
/**
 * If the player object intersects with this object, the game will switch to the battle
 * setting
 * 
 * get pokemon: 
 * pokemons are going to have number orders:
 * 1 for pikachu
 * 2 for rockdude etc
 * in the getPokemon(int x) method, each x will provide a corresponding wild pokemon
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grass extends Objects
{
    /*
     * Act - do whatever the Grass wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act() 
    { 
        //wildPokemonCollision();     
        setOriginalGrass();
        super.act();
    }

    private void setOriginalGrass(){        
        Player p1 = (Player)getOneIntersectingObject (Player.class);
        if (p1 == null){            
            GreenfootImage originalGrass = new GreenfootImage("grass 2x2.png");
            setImage(originalGrass);
            if (getWorld().getObjects(buttonE.class) != null){
                getWorld().removeObjects(getWorld().getObjects(buttonE.class));
            }
        }
        else
            return;
    }
}
