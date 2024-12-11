import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(800, 800, 1); 
        prepare();
    }
    
    public void prepare(){
        Sapo sapo = new Sapo();
        addObject(sapo,401,389);
        Aranha aranha = new Aranha();
        addObject(aranha,709,388);
        sapo.setLocation(473,507);
        Borboleta borboleta = new Borboleta();
        addObject(borboleta,473,507);
        Borboleta borboleta2 = new Borboleta();
        addObject(borboleta2,112,57);
        sapo.setLocation(608,388);
        Borboleta borboleta3 = new Borboleta();
        addObject(borboleta3,608,388);
        Borboleta borboleta4 = new Borboleta();
        addObject(borboleta4,90,383);
        Borboleta borboleta5 = new Borboleta();
        addObject(borboleta5,603,12);
        sapo.setLocation(235,518);
        Borboleta borboleta6 = new Borboleta();
        addObject(borboleta6,235,518);
        sapo.setLocation(210,217);
        Borboleta borboleta7 = new Borboleta();
        addObject(borboleta7,210,217);
        borboleta2.setLocation(310,19);
        Borboleta borboleta8 = new Borboleta();
        addObject(borboleta8,310,19);
        sapo.setLocation(259,539);
        borboleta.setLocation(306,652);
    }
}
