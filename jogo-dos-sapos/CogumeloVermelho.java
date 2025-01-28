import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CogumeloVermelho extends Cogumelos {
    public CogumeloVermelho() {
        super(3); // Duração de 3 segundos
        getImage().scale(80, 82);
    }

    @Override
    public void efeito(Sapo sapo, Boss boss) {
        sapo.aumentarVida(); // Aumenta a vida do sapo em 10
    }
}
