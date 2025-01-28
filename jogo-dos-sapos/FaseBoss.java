import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Segunda fase do jogo, onde o jogador enfrenta o Boss.
 */
public class FaseBoss extends World {
    private Placar placar;
    private Sapo sapo;
    private Boss boss;
    private long tempoUltimoVermelho = 0;
    private long tempoUltimoRoxo = 0;
    private long tempoUltimoAzul = 0;
    private boolean jogoFinalizado = false; // Flag para evitar múltiplas finalizações
    private long tempoInicio; // Para calcular a duração da partida
    private Som som;                // Gerenciador de sons

    public FaseBoss(Placar placar) {
        super(800, 600, 1);
        this.placar = placar;

        prepare();
    }

    private void prepare() {
        tempoInicio = System.currentTimeMillis(); // Marca o início da fase
        addObject(placar, getWidth() / 2, 50);
        placar.ativarExibicaoDeVida(); // mostra o placar de vida do sapo/boss

        boss = new Boss(placar); // Inicializa a variável de instância 'boss'
        addObject(boss, 700, 320);

        sapo = new Sapo();
        addObject(sapo, 422, 314);

        placar.atualizarVida(sapo.getVida(), boss.getVidaBoss()); // Atualiza a vida inicial
        
        // Inicializa o gerenciador de som
        som = new Som();
        som.adicionarSom("trilhaFinal", "trilha-final.mp3");
        
        // Toca a trilha sonora de fundo com volume reduzido
        som.ajustarVolume("trilhaFinal", 30); // Volume entre 0 e 100
        som.tocarSom("trilhaFinal");
    }

    public void act() {
        // Verifica condições de vitória ou derrota
        verificarEstadoJogo();

        // Atualiza a vida no placar
        if (sapo != null && boss != null) {
            placar.atualizarVida(sapo.getVida(), boss.getVidaBoss());
        }

        // Gera cogumelos de tempos em tempos
        gerarCogumelos();
    }

    private void verificarEstadoJogo() {
        // Vitória: se o Boss for derrotado (vida <= 0)
        if (!jogoFinalizado && boss.getVidaBoss() <= 0) {
            finalizarJogo(true); // Jogador venceu
        }

        // Derrota: se o Sapo for derrotado (vida <= 0)
        if (!jogoFinalizado && sapo.getVida() <= 0) {
            finalizarJogo(false); // Jogador perdeu
        }
    }

    /**
     * Finaliza o jogo e exibe a tela final.
     * 
     * @param venceu Indica se o jogador venceu ou perdeu.
     */
    private void finalizarJogo(boolean venceu) {
        jogoFinalizado = true; // Marca o jogo como finalizado
        som.pararSom("trilhaFinal");
        long tempoTotal = System.currentTimeMillis() - tempoInicio; // Calcula o tempo total da fase
        Greenfoot.setWorld(new TelaFinal(venceu, placar, tempoTotal)); // Troca para a tela final
    }

    private void gerarCogumelos() {
        Random random = new Random();

        // Gera cogumelo vermelho a cada 5 segundos
        if (System.currentTimeMillis() - tempoUltimoVermelho >= 5000) {
            addObject(new CogumeloVermelho(), random.nextInt(getWidth()), random.nextInt(getHeight()));
            tempoUltimoVermelho = System.currentTimeMillis();
        }

        // Gera cogumelo roxo a cada 5 segundos
        if (System.currentTimeMillis() - tempoUltimoRoxo >= 5000) {
            addObject(new CogumeloRoxo(), random.nextInt(getWidth()), random.nextInt(getHeight()));
            tempoUltimoRoxo = System.currentTimeMillis();
        }

        // Gera cogumelo azul a cada 8 segundos
        if (System.currentTimeMillis() - tempoUltimoAzul >= 8000) {
            addObject(new CogumeloAzul(), random.nextInt(getWidth()), random.nextInt(getHeight()));
            tempoUltimoAzul = System.currentTimeMillis();
        }
    }
}
