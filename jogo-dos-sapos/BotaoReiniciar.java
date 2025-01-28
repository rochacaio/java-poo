import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Botão que reinicia o jogo.
 */
public class BotaoReiniciar extends Actor {
    public BotaoReiniciar() {
    }

    @Override
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new Bosque()); // Reinicia o jogo na Fase 1
        }
    }
}
