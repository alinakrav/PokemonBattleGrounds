import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Trainer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trainer extends Objects
{
    Trainer(int type){
        if(type == 1){
            setImage(new GreenfootImage("Webp.net-resizeimage (10).png"));
        }else if(type == 2){
            setImage(new GreenfootImage("trainer 1.png"));
        }else if(type == 3){
            setImage(new GreenfootImage("Webp.net-resizeimage (12).png"));
        }else if(type == 4){
            setImage(new GreenfootImage("Webp.net-resizeimage (11).png"));
        }else if(type == 5){
            setImage(new GreenfootImage("Webp.net-resizeimage (9).png"));
        }
    }
}