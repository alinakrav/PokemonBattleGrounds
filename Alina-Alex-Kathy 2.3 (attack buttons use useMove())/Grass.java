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
    public Grass()
    {
        setImage(new GreenfootImage("grass 2x2.png"));
    } 

    public void collide() {
        System.out.println("grass collision");
    }
}
