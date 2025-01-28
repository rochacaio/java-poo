import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe genérica para gerenciar colisões entre objetos.
 * Superclasse de Aranha e Borboleta.
 * 
 * @author Jonathan Martins Cabral
 * @version 10/12/2024
 */
public abstract class Colisao extends Actor
{
    protected boolean estaViva; // Indica se o objeto está "vivo"

    /**
     * Inicializa o objeto e define como vivo.
     */
    public void inicializar()
    {
        estaViva = true;
    }

    /**
     * Retorna se o objeto está vivo.
     * @return true se está vivo, false caso contrário.
     */
    public boolean estaViva()
    {
        return estaViva;
    }

    /**
     * Verifica se houve colisão com um objeto específico.
     * 
     * @param alvo Classe do objeto alvo da colisão.
     * @param distanciaMaxima Distância máxima para considerar colisão.
     * @return true se houve colisão, false caso contrário.
     */
    protected boolean verificarColisao(Class<? extends Actor> alvo, int distanciaMaxima) {
        if (estaViva && getWorld() != null && !getWorld().getObjects(alvo).isEmpty()) {
            Actor objeto = (Actor) getWorld().getObjects(alvo).get(0);
            int distancia = (int) Math.hypot(getX() - objeto.getX(), getY() - objeto.getY());
            return distancia < distanciaMaxima;
        }
        return false;
    }
}