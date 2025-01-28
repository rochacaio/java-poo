import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Superclasse para cogumelos.
 */
public abstract class Cogumelos extends Actor {
    protected int duracao; // Tempo em segundos para o cogumelo desaparecer
    protected long tempoInicial; // Momento em que o cogumelo foi criado

    public Cogumelos(int duracao) {
        this.duracao = duracao * 1000; // Converte duração para milissegundos
        tempoInicial = System.currentTimeMillis(); // Registra o tempo de criação
    }

    @Override
    public void act() {
        if (System.currentTimeMillis() - tempoInicial >= duracao) {
            getWorld().removeObject(this); // Remove o cogumelo após sua duração
        }
    }

    /**
     * Método abstrato para ser implementado pelos cogumelos específicos.
     */
    public abstract void efeito(Sapo sapo, Boss boss);
}
