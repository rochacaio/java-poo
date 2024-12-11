import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Borboleta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Borboleta extends Actor
{
    /**
     * Act - do whatever the Borboleta wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(2);
        if (Greenfoot.getRandomNumber(100) < 10) {
            int angulo = Greenfoot.getRandomNumber(90) - 45;
            turn(angulo);
        }   

    }
}
