import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tela inicial do Jogo dos Sapos.
 * 
 * @author Jonathan Martins Cabral / Caio César da Rocha 
 * @version 10/12/2024
 */
public class TelaInicial extends World
{
    public TelaInicial()
    {    
        super(800, 600, 1);
        prepararTela();
    }

    private void prepararTela()
    {
        // Define o fundo
        GreenfootImage fundo = new GreenfootImage(getWidth(), getHeight());
        fundo.setColor(new Color(34, 139, 34)); // Cor verde
        fundo.fill();
        
        // Título do jogo
        GreenfootImage titulo = new GreenfootImage("JOGO DOS SAPOS", 60, Color.YELLOW, null);
        fundo.drawImage(titulo, getWidth() / 2 - titulo.getWidth() / 2, 100); // Centralizado

        // Informações sobre os desenvolvedores, disciplina e período
        GreenfootImage info = new GreenfootImage("Desenvolvido por: Jonathan Martins Cabral e Caio César da Rocha\nDisciplina: Introdução a Programação Orientada a Objetos (2024-2)", 
                                                 20, Color.WHITE, null);
        fundo.drawImage(info, getWidth() / 2 - info.getWidth() / 2, 200);

        // Opções para o jogador
        GreenfootImage iniciar = new GreenfootImage("Pressione [ENTER] para iniciar o jogo", 30, Color.WHITE, null);
        GreenfootImage instrucoes = new GreenfootImage("Pressione [I] para instruções", 30, Color.WHITE, null);
        fundo.drawImage(iniciar, getWidth() / 2 - iniciar.getWidth() / 2, 300);
        fundo.drawImage(instrucoes, getWidth() / 2 - instrucoes.getWidth() / 2, 350);

        // Define o fundo no mundo
        setBackground(fundo);
    }

    public void act()
    {
        // Verifica se o jogador pressionou ENTER para começar o jogo
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new Bosque()); // Mundo principal (Bosque)
        }
        // Verifica se o jogador pressionou I para ver as instruções
        if (Greenfoot.isKeyDown("i")) {
            Greenfoot.setWorld(new TelaInstrucoes()); // Mundo das instruções
        }
    }
}

/**
 * Tela de Instruções do jogo.
 */
class TelaInstrucoes extends World
{
    public TelaInstrucoes()
    {    
        super(800, 600, 1);
        prepararTela();
    }

    private void prepararTela()
    {
        // Define o fundo
        GreenfootImage fundo = new GreenfootImage(getWidth(), getHeight());
        fundo.setColor(new Color(70, 130, 180)); // Cor azul
        fundo.fill();
        
        // Título
        GreenfootImage titulo = new GreenfootImage("INSTRUÇÕES DO JOGO", 50, Color.WHITE, null);
        fundo.drawImage(titulo, getWidth() / 2 - titulo.getWidth() / 2, 50);

        // Texto das instruções
        String texto = "CONTEXTO:\nVocê controla um sapo em busca de borboletas para coletar pontos para batalhar com o bruxo de pedra. \n\n"
                     + "CONTROLES:\n- Use as setas do teclado para mover o sapo e a tecla 'SPACE' para atirar.\n DETALHE: Você consegue atirar somente ao passar para fase do bruxo de pedra\n\n"
                     + "OBJETIVO:\n- Colete 100 pontos em 40 segundos para vencer a aranha monstruosa.\n"
                     + "  Se não alcançar a pontuação, o sapo será derrotado pela aranha!\n\n"
                     + "  Após coletar 100 pontos, você ira para fase final do grande boss, nela você tem cogumelos mágicos!\n"
                     + "  Cada cogumelo faz algo com o sapo ou o boss: \n-Os vermelhos aumentam em 10 a vida do sapo.\n-Os roxos tiram 10 de vida do boss. \n-O azul tiram 20 de vida do sapo.\n\n"
                     + "  Para conseguir derrotar o boss: \nVocê vai atirar mini borboletas a ele que o causam dano; \nEnquanto ele solta aranhas pelo corpo que diminuem sua vida.\n\n"
                     + "> Pressione [B] para voltar à tela inicial.";
        GreenfootImage instrucoes = new GreenfootImage(texto, 20, Color.WHITE, null);
        fundo.drawImage(instrucoes, getWidth() / 2 - instrucoes.getWidth() / 2, 150);

        // Define o fundo no mundo
        setBackground(fundo);
    }

    public void act()
    {
        // Verifica se o jogador pressionou B para voltar à tela inicial
        if (Greenfoot.isKeyDown("b")) {
            Greenfoot.setWorld(new TelaInicial()); // Volta para a tela inicial
        }
    }
}
