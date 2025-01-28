import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Essa classe é o "boss" do nosso pequeno código.
 * 
 * @author 
 * @version 10/12/2024
 */
public class Aranha extends Colisao
{
    private Placar placar; // Referência ao placar
    private int valor;     // Pontuação do sapo ao criar a aranha
    private Som som;       // Gerenciador de sons

    public Aranha(Placar placar)
    {
        this.placar = placar;
        this.som = new Som(); // Inicializa o gerenciador de sons
        inicializar();
        valor = placar.sapoComeu();

        // Adiciona os sons usados nesta classe
        som.adicionarSom("derrota", "derrota.mp3");
        som.adicionarSom("vitoria", "vitoria.mp3");
        
    }

    @Override
    public void act()
    {
        if (estaViva) {
            if (!getWorld().getObjects(Sapo.class).isEmpty()) {
                Sapo sapo = getWorld().getObjects(Sapo.class).get(0);
                turnTowards(sapo.getX(), sapo.getY());
                move(5);

                // Verifica colisão com o sapo
                if (verificarColisao(Sapo.class, 35)) {
                    dueloFinal();
                }
            }
        }
    }

    private void dueloFinal()
    {
        if (valor < 100) { // O sapo perdeu
            derrota();
        } else { // O sapo venceu
            vitoria();
        }
    }

    private void exibirMensagem(String mensagemTexto)
    {
        GreenfootImage mensagem = new GreenfootImage(mensagemTexto, 30, Color.WHITE, Color.BLACK);
        getWorld().getBackground().drawImage(mensagem, getWorld().getWidth() / 2 - mensagem.getWidth() / 2, getWorld().getHeight() / 2 - mensagem.getHeight() / 2);
    }
    
    private void derrota(){
        som.ajustarVolume("derrota", 20); // Ajusta o volume do som de derrota (0 a 100)
            som.tocarSom("derrota");

            if (!getWorld().getObjects(Sapo.class).isEmpty()) {
                Sapo sapo = getWorld().getObjects(Sapo.class).get(0);
                getWorld().removeObject(sapo);
            }
            exibirMensagem("Você perdeu! O sapo não conseguiu se fortalecer.");
            Greenfoot.stop();
    }
    
    private void vitoria(){
        som.ajustarVolume("vitoria", 20); // Ajusta o volume do som de vitória (0 a 100)
            som.tocarSom("vitoria");
            
            exibirMensagem("Você ganhou! O sapo conseguiu se fortalecer. Parabéns!");
            estaViva = false;

            // Transição para a FaseBoss
            World faseBoss = new FaseBoss(placar); // Cria a nova fase com o placar atual
            Greenfoot.setWorld(faseBoss); // Define o novo mundo

            getWorld().removeObject(this); // Remove a aranha do mundo atual
    }
}
