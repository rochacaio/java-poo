import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe para representar as mini borboletas disparadas pelo sapo.
 */
public class MiniBorboleta extends Colisao
{
    private int velocidade; // Velocidade da mini borboleta
    private int dano;       // Dano causado ao acertar o boss

    /**
     * Construtor da MiniBorboleta.
     */
    public MiniBorboleta()
    {
        velocidade = 5; // Define a velocidade padrão
        dano = 5;       // Define o dano que a mini borboleta causará
        inicializar();  // Define o estado inicial da mini borboleta
    }

    /**
     * Método principal da mini borboleta (executado a cada frame).
     * Move a mini borboleta para frente e verifica colisões.
     */
    public void act()
    {
        if (!estaViva) {
            return; // Para a execução se a mini borboleta não estiver viva
        }

        move(velocidade); // Move a mini borboleta para frente

        // Verifica colisão com o boss
        if (verificarColisao(Boss.class, 60)) {
            Boss boss = (Boss) getWorld().getObjects(Boss.class).get(0); // Obtém o boss
            boss.receberDano(dano); // Aplica o dano ao boss
            estaViva = false; // Marca como "não viva"
            getWorld().removeObject(this); // Remove do mundo
            return; // Para o método imediatamente
        }

        // Remove a mini borboleta se sair do mundo
        if (isAtEdge()) {
            estaViva = false; // Marca como "não viva"
            getWorld().removeObject(this); // remove objeto do mundo
            return; // Para o método imediatamente
        }
    }
}
