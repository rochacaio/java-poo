import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Classe do personagem principal do jogo.
 */
public class Sapo extends Actor
{
    private int velocidade;   // Velocidade de movimento do sapo
    private boolean estaVivo; // Indica se o sapo está vivo
    private int vida;         // Vida do sapo
    private int cooldown;     // Cooldown para disparar mini borboletas

    /**
     * Construtor da classe Sapo.
     */
    public Sapo()
    {
        velocidade = 3;       // Define a velocidade padrão do sapo
        inicializar();        // Inicializa os atributos do sapo
    }

    public void inicializar()
    {
        setLocation(400, 300); // Posiciona o sapo no centro do mundo (ajuste conforme necessário)
        estaVivo = true;       // Marca o sapo como vivo
        vida = 100;            // Define a vida inicial do sapo
        cooldown = 0;          // Sem cooldown inicial
    }

    public void reduzirVida(int dano) {
        vida -= dano; // Reduz a vida com base no dano recebido
        if (vida <= 0) { // verifica se o sapo esta vivo
            vida = 0;
            estaVivo = false; // Marca como morto se a vida chegar a 0
            if (getWorld() != null) { // Verifica se o sapo ainda está no mundo
                getWorld().removeObject(this); // Remove o sapo do mundo
            }
        }
    }

    public int getVida() {
        return vida;
    }

    public void aumentarVida() {
        vida += 10;
    }

    public boolean estaVivo() {
        return estaVivo;
    }

    /**
     * Método principal do sapo (executado a cada frame).
     */
    public void act()
    {
        if (getWorld() == null) {
            return; // Se o sapo não está no mundo, não faz nada
        }

        // Verifica colisões com cogumelos
        List<Cogumelos> cogumelos = getWorld().getObjects(Cogumelos.class);
        for (Cogumelos cogumelo : cogumelos) {
            int distancia = (int) Math.hypot(getX() - cogumelo.getX(), getY() - cogumelo.getY());
            if (distancia <= 30) { // Apenas consome se estiver a 30 pixels de distância
                Boss boss = getWorld().getObjects(Boss.class).isEmpty() ? null : getWorld().getObjects(Boss.class).get(0);
                cogumelo.efeito(this, boss); // Aplica o efeito do cogumelo

                // Verifica se o mundo ainda existe antes de remover o cogumelo
                if (getWorld() != null) {
                    getWorld().removeObject(cogumelo); // Remove o cogumelo do mundo
                }
                break; // Interrompe o loop após consumir um cogumelo
            }
        }
        if (!estaVivo) {
            return;
        }

        // Atualiza o cooldown
        if (cooldown > 0) {
            cooldown--; // Reduz o cooldown a cada frame
        }

        // Movimentação
        if (Greenfoot.isKeyDown("up")) moverCima();
        if (Greenfoot.isKeyDown("down")) 
            moverBaixo();
        if (Greenfoot.isKeyDown("right")) 
            moverDireita();
        if (Greenfoot.isKeyDown("left")) 
            moverEsquerda();

        // Disparo apenas na FaseBoss
        if (getWorld() instanceof FaseBoss) {
            // Controle de disparo com cooldown
            if (Greenfoot.isKeyDown("space") && cooldown == 0) {
                dispararMiniBorboleta();
                cooldown = 180; // Define um cooldown de 3 segundos (60 FPS * 3 segundos)
            }
        }
    }

    public void moverCima() {
        setLocation(getX(), getY() - velocidade);
    }

    public void moverBaixo() {
        setLocation(getX(), getY() + velocidade);
    }

    public void moverDireita() {
        setLocation(getX() + velocidade, getY());
    }

    public void moverEsquerda() {
        setLocation(getX() - velocidade, getY());
    }

    /**
     * Cria e dispara uma mini borboleta.
     */
    private void dispararMiniBorboleta()
    {
        MiniBorboleta miniBorboleta = new MiniBorboleta(); // Cria a mini borboleta
        getWorld().addObject(miniBorboleta, getX(), getY() - 20); // Adiciona no mundo na posição do sapo
    }
}
