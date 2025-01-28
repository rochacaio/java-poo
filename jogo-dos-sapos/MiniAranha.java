import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Mini aranha disparada pelo Boss.
 */
public class MiniAranha extends Colisao {
    private int velocidade;

    public MiniAranha(int direcao) {
        inicializar();
        velocidade = 2; // Velocidade da mini aranha
        setRotation(direcao); // Define a direção inicial
    }

    @Override
    public void act() {
        if (getWorld() == null) {
            return; // Sai do método se o objeto não estiver no mundo
        }

        if (estaViva) {
            move(velocidade);

            if (isAtEdge()) { // remove a aranha quando ela chega a borda do mundo
                getWorld().removeObject(this);
            }

            if (verificarColisao(Sapo.class, 30)) { // verifica se a aranha atingiu o sapo
                atingirSapo();
            }
        }
    }

    /*
     * Método que verifica se existe sapo no mundo para ser atingindo
     * caso exista ele reduz a vida dele, caso contrário ele finaliza o mundo
     */
    private void atingirSapo() 
    {
        if (!getWorld().getObjects(Sapo.class).isEmpty()) {
            Sapo sapo = getWorld().getObjects(Sapo.class).get(0);
            sapo.reduzirVida(Greenfoot.getRandomNumber(11)); // Causa de 0 a 10 de dano
        }
        getWorld().removeObject(this);
    }

}
