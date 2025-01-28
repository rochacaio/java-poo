import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tela de fim de jogo que exibe o resultado (vitória/derrota), pontuação e duração da partida.
 */
public class TelaFinal extends World {
    private boolean venceu; // Indica se o jogador venceu ou perdeu
    private Placar placar;  // Referência ao placar do jogo
    private long tempoTotal; // Tempo total da partida em milissegundos

    /**
     * Construtor da tela final.
     * 
     * @param venceu Indica se o jogador venceu ou perdeu.
     * @param placar Referência ao placar do jogo para exibir pontuações.
     * @param tempoTotal Tempo total de jogo em milissegundos.
     */
    public TelaFinal(boolean venceu, Placar placar, long tempoTotal) {
        super(800, 600, 1); // Define o tamanho da tela
        this.venceu = venceu;
        this.placar = placar;
        this.tempoTotal = tempoTotal;

        // Adiciona um fundo colorido
        setBackground(new GreenfootImage(800, 600));
        desenharFundo();

        // Mostra informações estilizadas e botão de reiniciar
        mostrarInformacoes();
        adicionarBotaoReiniciar();
    }

    /**
     * Desenha o fundo da tela com uma cor agradável.
     */
    private void desenharFundo() {
        GreenfootImage fundo = getBackground();
        if (venceu) {
            fundo.setColor(new Color(50, 205, 50)); // Verde claro para vitória
        } else {
            fundo.setColor(new Color(220, 20, 60)); // Vermelho escuro para derrota
        }
        fundo.fillRect(0, 0, getWidth(), getHeight()); // Preenche o fundo com a cor
    }

    /**
     * Mostra as informações do fim de jogo com estilos mais bonitos.
     */
    private void mostrarInformacoes() {
        // Mensagem de vitória ou derrota estilizada
        String mensagem = venceu ? "PARABÉNS! VOCÊ VENCEU!" : "GAME OVER! TENTE NOVAMENTE!";
        int tamanhoTexto = venceu ? 40 : 35; // Ajusta o tamanho do texto dependendo do resultado
        GreenfootImage textoMensagem = criarTextoEstilizado(mensagem, tamanhoTexto, Color.YELLOW, Color.BLACK);
        getBackground().drawImage(textoMensagem, (getWidth() - textoMensagem.getWidth()) / 2, 100);

        // Exibe os pontos do placar
        int pontos = placar.sapoComeu();
        int borboletasNecessarias = Math.max(0, 100 - pontos); // Garante que não seja negativo
        GreenfootImage textoPontos = criarTextoEstilizado(
            "Borboletas comidas: " + pontos,
            30,
            Color.WHITE,
            Color.BLACK
        );
        getBackground().drawImage(textoPontos, (getWidth() - textoPontos.getWidth()) / 2, 200);

        // Duração da partida
        long segundos = ((tempoTotal / 1000) % 60) + 40;
        long minutos = (tempoTotal / 1000) / 60;
        GreenfootImage textoDuracao = criarTextoEstilizado(
            "Duração da partida: " + segundos + "s",
            30,
            Color.WHITE,
            Color.BLACK
        );
        getBackground().drawImage(textoDuracao, (getWidth() - textoDuracao.getWidth()) / 2, 300);

        // Adicional: Exibe mensagem de incentivo para tentar novamente
        String mensagemIncentivo = venceu ? "Fantástico! Você derrotou o Boss!" : "Não desista, tente outra vez!";
        GreenfootImage textoIncentivo = criarTextoEstilizado(
            mensagemIncentivo,
            25,
            Color.LIGHT_GRAY,
            Color.BLACK
        );
        getBackground().drawImage(textoIncentivo, (getWidth() - textoIncentivo.getWidth()) / 2, 400);
    }

    /**
     * Adiciona um botão para reiniciar o jogo.
     */
    private void adicionarBotaoReiniciar() {
        BotaoReiniciar botao = new BotaoReiniciar();
        addObject(botao, getWidth() / 2, getHeight() - 100);
    }

    /**
     * Cria um texto estilizado com sombra para melhor visualização.
     * 
     * @param texto O texto a ser exibido.
     * @param tamanho O tamanho da fonte.
     * @param corTexto A cor do texto.
     * @param corSombra A cor da sombra.
     * @return Uma imagem do texto estilizado.
     */
    private GreenfootImage criarTextoEstilizado(String texto, int tamanho, Color corTexto, Color corSombra) {
        GreenfootImage textoImg = new GreenfootImage(texto, tamanho, corTexto, new Color(0, 0, 0, 0));
        GreenfootImage sombraImg = new GreenfootImage(texto, tamanho, corSombra, new Color(0, 0, 0, 0));

        GreenfootImage imagemFinal = new GreenfootImage(textoImg.getWidth() + 2, textoImg.getHeight() + 2);
        imagemFinal.drawImage(sombraImg, 2, 2); // Adiciona sombra deslocada
        imagemFinal.drawImage(textoImg, 0, 0); // Adiciona o texto principal

        return imagemFinal;
    }
}
