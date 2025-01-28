import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CogumeloRoxo extends Cogumelos {
    public CogumeloRoxo() {
        super(3); // Duração de 3 segundos
        getImage().scale(80, 82);
    }

    @Override
    public void efeito(Sapo sapo, Boss boss) {
        if (boss != null) {
            boss.receberDano(10); // Reduz a vida do boss em 10
        }
    }
}
