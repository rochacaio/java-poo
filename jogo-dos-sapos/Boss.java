import greenfoot.*;
import java.util.Random;

public class Boss extends Actor {
    private Placar placar;
    private int contadorTiros; // Contador para controlar os tiros
    private int vidaBoss; // Vida do boss
    private boolean estaVivo;
    private int direcaoMovimento; // Direção do movimento (-1 para cima, 1 para baixo)
    private int velocidade; // Velocidade do movimento
    private int limiteSuperior; // Limite superior do movimento
    private int limiteInferior; // Limite inferior do movimento
    private Random random; // Gerador de números aleatórios

    public Boss(Placar placar) {
        this.placar = placar;
        contadorTiros = 0;
        vidaBoss = 100; // Define a vida inicial do boss
        estaVivo = true;
        velocidade = 2; // Velocidade padrão do Boss
        direcaoMovimento = 0; // Inicialmente parado
        random = new Random(); // Inicializa o gerador de números aleatórios

        // Define os limites de movimento (ajustado para Y = 174 e Y = 354)
        limiteSuperior = 174; // Altura mínima que o Boss pode alcançar
        limiteInferior = 449; // Altura máxima que o Boss pode alcançar
    }

    public void receberDano(int dano) {
        vidaBoss -= dano; // Reduz a vida do boss
        if (vidaBoss <= 0) {
            vidaBoss = 0;
            estaVivo = false;
            getWorld().removeObject(this); // Remove o boss se a vida chegar a 0
        }
    }

    public int getVidaBoss() {
        return vidaBoss;
    }

    public boolean estaVivo() {
        return estaVivo;
    }

    public void act() {
        // Incrementa o contador
        contadorTiros++;

        // Dispara uma mini aranha a cada 100 frames (~2 segundos)
        if (contadorTiros >= 100) {
            dispararMiniAranha();
            contadorTiros = 0; // Reinicia o contador
        }

        // Atualiza o movimento do Boss
        moverAleatoriamente();
    }

    private void dispararMiniAranha() {
        if (!getWorld().getObjects(Sapo.class).isEmpty()) {
            Sapo sapo = getWorld().getObjects(Sapo.class).get(0);

            int direcao = (int) Math.toDegrees(Math.atan2(sapo.getY() - getY(), sapo.getX() - getX()));

            MiniAranha miniAranha = new MiniAranha(direcao);
            getWorld().addObject(miniAranha, getX(), getY()); // Adiciona a MiniAranha ao mundo
        }
    }

    private void moverAleatoriamente() {
        // Gera um novo valor de direção em intervalos aleatórios
        if (random.nextInt(50) == 0) { // 1/50 de chance de mudar de direção a cada frame
            direcaoMovimento = random.nextInt(3) - 1; // -1 para cima, 0 para parado, 1 para baixo
        }

        // Move o Boss de acordo com a direção
        int novoY = getY() + direcaoMovimento * velocidade;

        // Verifica os limites antes de atualizar a posição
        if (novoY >= limiteSuperior && novoY <= limiteInferior) {
            setLocation(getX(), novoY);
        }
    }
}
