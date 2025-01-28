import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CogumeloAzul extends Cogumelos {
    public CogumeloAzul() {
        super(4); // Duração de 4 segundos
        getImage().scale(80, 82);
    }

    @Override
    public void efeito(Sapo sapo, Boss boss) {
        sapo.reduzirVida(20); // Reduz a vida do sapo em 20
    }
}
