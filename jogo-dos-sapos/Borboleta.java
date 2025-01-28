import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * As salvadoras do nosso querido e amado sapo!
 * Essa classe é as borboletas que fortalecem o sapo para combater a aranha
 * 
 * @author Jonathan Martins Cabral / Caio César da Rocha 
 * @version 10/12/2024
 */
public class Borboleta extends Colisao
{
    private int velocidade;
    private Placar placar;

    public Borboleta(Placar placar)
    {
        this.placar = placar;
        velocidade = 3;
        inicializar();
    }

    @Override
    public void act()
    {
        move(velocidade);

        // Chance de mudar de direção
        if (Greenfoot.getRandomNumber(100) < 10) {
            turn(Greenfoot.getRandomNumber(90) - 45);
        }

        // Ajusta direção ao tocar na borda
        if (isAtEdge()) {
            turn(180 + Greenfoot.getRandomNumber(90) - 45);
        }

        // Verifica colisão com o sapo
        if (verificarColisao(Sapo.class, 35)) {
            tomarLinguada();
        }
    }

    private void tomarLinguada()
    {
        if (estaViva) {
            estaViva = false;
            placar.contarBorboletasComidas();

            // Cria nova borboleta
            Bosque bosque = (Bosque) getWorld();
            bosque.criarBorboleta();

            getWorld().removeObject(this);
        }
    }
}
