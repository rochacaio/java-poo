import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Representa o placar do nosso jogo, como por exemplo a quantidade necessária de borboletas que faltam e a quantidade já obtida.
 * 
 * @author Jonathan Martins Cabral / Caio César da Rocha 
 * @version 10/12/2024
 */
public class Placar extends Actor
{
    private int pontuacao; // Pontuação atual do sapo (quantidade de borboletas comidas)
    private int vidaSapo;  // Vida atual do sapo
    private int vidaBoss; // Vida atual do boss
    private int tempoPartida; // Variável que armazena o tempo que resta de jogo
    private boolean placar_tempo; // Verifica se o placar é para pontuação ou tempo de partida restante
    private boolean exibirVida; // Controla se a vida deve ser exibida
    private long ultimoAtualizado = System.currentTimeMillis(); // Marca o tempo inicial

    /**
     * Construtor para objetos do placar.
     * Inicializa o placar e define sua posição no mundo.
     */
    public Placar(boolean placar_tempo, int tempoPartida)
    {
        this.placar_tempo = placar_tempo; // Configura se o placar exibirá o tempo restante
        this.tempoPartida = tempoPartida; // Inicializa o tempo restante com o valor informado

        // Define a localização inicial do placar (ajustada no mundo)
        setLocation(20, 20);

        // Inicializa o placar
        inicializar();
    }

    /**
     * Reinicia o placar para seu estado inicial.
     */
    public void inicializar()
    {        
        // Reinicia a pontuação para 0
        pontuacao = 0;

        // Inicializa as vidas do sapo e do boss com os valores máximos
        vidaSapo = 100; 
        vidaBoss = 100;

        // Por padrão, a exibição da vida estará desativada
        exibirVida = false;

        // Redefine a imagem do placar para refletir a pontuação inicial
        redefinirImagem();
    }

    public void ativarExibicaoDeVida()
    {
        exibirVida = true; // Ativa a exibição da vida do sapo e do boss
        redefinirImagemBoss(); // Atualiza a imagem para incluir a vida do boss
    }

    public void atualizarVida(int vida, int vidab)
    {
        if (exibirVida) { 
            vidaSapo = vida; // Atualiza a vida do sapo com o valor fornecido
            vidaBoss = vidab; // Atualiza a vida do boss com o valor fornecido
            redefinirImagemBoss(); // Atualiza a imagem para refletir as novas vidas
        }
    }

    /**
     * Atualiza a pontuação do placar ao contabilizar borboletas comidas.
     */
    public void contarBorboletasComidas()
    {
        // Incrementa a pontuação de forma aleatória (entre 1 e 10)
        pontuacao += Greenfoot.getRandomNumber(10) + 1;

        // Atualiza a imagem do placar para refletir a nova pontuação
        redefinirImagem();
    }

    /**
     * Atualiza a imagem do placar de acordo com a pontuação ou o tempo restante.
     */
    public void redefinirImagem() {
        if (placar_tempo) {
            definirPlacarTempo(); // Atualiza a imagem do placar para exibir o tempo restante
        } else {
            definirPlacarPontos(); // Atualiza a imagem do placar para exibir a pontuação
        }
    }

    /**
     * Atualiza a imagem do placar de acordo com a vida do sapo e do boss.
     */
    public void redefinirImagemBoss() {

        String texto = "Vida do Sapo: " + vidaSapo + 
                    "\nVida do boss:" + vidaBoss;
        
        // Atualiza a imagem do placar com o texto configurado
        setImage(new GreenfootImage(texto, 21, Color.YELLOW, Color.BLACK));
    }
    
    /**
     * Retorna a pontuação atual do sapo (quantidade de borboletas comidas).
     * @return A pontuação atual.
     */
    public int sapoComeu()
    {
        return pontuacao; // Retorna o número de borboletas comidas
    }
    
    private void definirPlacarPontos() {
        // Calcula o número de borboletas ainda necessárias
        int borboletasNecessarias = 100 - pontuacao;

        // Garante que o valor nunca fique negativo
        if (borboletasNecessarias < 0) {
            borboletasNecessarias = 0;
        }

        // Define o texto a ser exibido no placar
        String texto = "Borboletas comidas: " + pontuacao + "\nBorboletas necessárias: " + borboletasNecessarias;

        // Atualiza a imagem do placar com o texto configurado
        setImage(new GreenfootImage(texto, 21, Color.YELLOW, Color.BLACK));
    }
    
    private void definirPlacarTempo() {
        String texto = "Tempo restante: " + tempoPartida + "s"; // Texto para exibir o tempo restante
        setImage(new GreenfootImage(texto, 21, Color.YELLOW, Color.BLACK)); // Atualiza o placar com o tempo
    }

    /**
     * O placar não faz nada a cada iteração do jogo.
     */
    public void act()
    {
        // Não faz nada
    }
    
    // Método que atualiza o tempo do placar de tempo da primeira fase do jogo
    public void atualizarTempo() {
        // Verifica se 1 segundo se passou
        if (System.currentTimeMillis() - ultimoAtualizado >= 1000) {
            tempoPartida--; // Reduz um segundo do tempo restante
            ultimoAtualizado = System.currentTimeMillis(); // Atualiza o último tempo registrado
        }

        // Garante que o tempo restante não fique negativo
        if (tempoPartida <= 0) {
            tempoPartida = 0; // Define o tempo restante como 0
        }

        // Atualiza a imagem do placar para refletir o tempo restante
        redefinirImagem();
    }
}
