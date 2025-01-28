import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;

/**
 * Bosque onde a saga do sapo se inicia.
 * 
 * @author Jonathan Martins Cabral / Caio César 
 * @version 10/12/2024
 */
public class Bosque extends World
{
    private Placar placar;          // Placar do jogo que mostra a pontuação do jogador
    private Placar placar_tempo;    // Placar do jogo que mostra o tempo restante da fase
    private int contadorTempo;      // Contador para rastrear o tempo total em frames
    private int contadorSpawn;      // Contador para controlar o intervalo entre a criação de borboletas
    private boolean criarBorboletas;// Flag para indicar se as borboletas ainda devem ser criadas
    private boolean aranhaCriada;   // Flag para indicar se a aranha já foi criada
    private Som som;                // Gerenciador de sons do jogo

    /**
     * Construtor de objetos do Bosque. 
     * Inicializa o ambiente de jogo.
     */
    public Bosque()
    {    
        super(800, 600, 1); // Configura o tamanho do mundo (800x600 pixels) e escala (1)
        prepare(); // Configura os elementos iniciais do jogo
    }

    /**
     * Método que prepara o mundo inicial.
     */
    private void prepare()
    {
        // Inicializa os placares: um para pontuação e outro para o tempo
        placar = new Placar(false, 0);          // Placar que exibe pontuações
        placar_tempo = new Placar(true, 40);   // Placar que exibe o tempo restante da fase

        // Adiciona os placares ao mundo em posições específicas
        addObject(placar, 110, 20); // Coloca o placar no canto superior esquerdo
        addObject(placar_tempo, 710, 20); // Coloca o placar de tempo no canto superior direito

        // Cria o objeto do sapo e o posiciona no centro do mundo
        Sapo sapo = new Sapo();
        addObject(sapo, 422, 314);

        // Inicializa os contadores
        contadorTempo = 0;         // Define o contador de tempo como 0
        contadorSpawn = 0;         // Define o contador de spawn como 0

        // Define que borboletas podem ser criadas inicialmente
        criarBorboletas = true;    

        // Define que a aranha ainda não foi criada
        aranhaCriada = false;      

        // Cria a primeira borboleta no mundo
        criarBorboleta();

        // Inicializa o gerenciador de som e configura os sons do jogo
        som = new Som();
        som.adicionarSom("ataqueAranha", "ataque-aranha.mp3"); // Adiciona som para o ataque da aranha
        som.adicionarSom("trilhaFundo", "trilha-inicial.mp3"); // Adiciona a trilha sonora inicial

        // Configura o volume da trilha sonora de fundo
        som.ajustarVolume("trilhaFundo", 10); // Define o volume da trilha como 10%
        som.tocarSom("trilhaFundo"); // Começa a tocar a trilha sonora
    }

    /**
     * Método chamado a cada frame do jogo.
     */
    public void act()
    {   
        placar_tempo.atualizarTempo(); // Atualiza o placar de tempo

        contadorTempo++; // Incrementa o contador total de tempo em frames

        // Após 40 segundos (2000 frames em 50 FPS), as borboletas param de aparecer e a aranha surge
        if (contadorTempo >= 4000) {
            criarBorboletas = false; // Para de criar novas borboletas
            removerTodasBorboletas(); // Remove todas as borboletas existentes no mundo

            // Cria a aranha apenas uma vez
            if (!aranhaCriada) {
                criarAranha();
                aranhaCriada = true; // Marca que a aranha foi criada
            }
        }

        // Cria borboletas a cada 100 frames (~2 segundos) enquanto permitido
        if (criarBorboletas) {
            contadorSpawn++; // Incrementa o contador de spawn
            if (contadorSpawn >= 100) { // Se o contador de spawn alcançar 100 frames
                criarBorboleta();       // Cria uma nova borboleta
                contadorSpawn = 0;      // Reinicia o contador de spawn
            }
        }
    }

    /**
     * Cria uma nova borboleta em uma posição aleatória no mundo.
     */
    public void criarBorboleta()
    {   
        // Cria uma nova borboleta associada ao placar
        Borboleta borboleta = new Borboleta(placar); 

        // Calcula uma posição aleatória dentro de uma área válida do mundo
        int x = Greenfoot.getRandomNumber(583) + 118; // Posição X entre 118 e 701
        int y = Greenfoot.getRandomNumber(409) + 116; // Posição Y entre 116 e 525

        // Adiciona a borboleta ao mundo na posição calculada
        addObject(borboleta, x, y); 
    }

    /**
     * Cria a aranha no mundo e toca o som correspondente.
     */
    public void criarAranha()
    {
        // Cria uma nova aranha associada ao placar
        Aranha aranha = new Aranha(placar); 

        // Adiciona a aranha em uma posição fixa no mundo
        addObject(aranha, 11, 307);

        // Toca o som de ataque da aranha
        som.tocarSom("ataqueAranha");
    }

    /**
     * Remove todas as borboletas existentes no mundo.
     */
    public void removerTodasBorboletas()
    {
        // Obtém todas as borboletas existentes no mundo e as remove
        for (Borboleta borboleta : getObjects(Borboleta.class)) {
            removeObject(borboleta);
        }

        // Para a trilha sonora de fundo
        som.pararSom("trilhaFundo");
    }
}
