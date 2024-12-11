import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Aranha here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Aranha extends Actor
{
    /**
     * Act - do whatever the Aranha wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(4);
        if (Greenfoot.getRandomNumber(100) < 10) {
            int angulo = Greenfoot.getRandomNumber(90) - 45;
            turn(angulo);
        }
    
        if (isAtEdge()) {
            turn(180);
        }
        
        //Actor sapo = getOneIntersectingObject(Sapo.class);
        //if (sapo != null) {
//            World mundo = getWorld();
        //    mundo.removeObject(sapo);
       //     Greenfoot.playSound("comendo.wav");
        //}
    }    
}
