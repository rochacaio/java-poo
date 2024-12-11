import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe do personagem principal do jogo, o sapo que terá a missão de comer as borboletas para se alimentar e passar de fase para derrotar o chefão
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sapo extends Actor
{
    /**
     * Act - do whatever the Sapo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(4);
        
        if (Greenfoot.isKeyDown("left")) {    
            turn(-3);   
        }
        if (Greenfoot.isKeyDown("right")) {    
            turn(3);   
        }
        
        Actor borboleta = getOneIntersectingObject(Borboleta.class);

        if (borboleta != null) {
            World mundo = getWorld();
            mundo.removeObject(borboleta);
            Greenfoot.playSound("comendo.wav");
        }
    }
}
