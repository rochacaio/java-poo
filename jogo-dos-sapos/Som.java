import greenfoot.GreenfootSound;
import java.util.HashMap;

public class Som {
    // HashMap para armazenar os sons
    private HashMap<String, GreenfootSound> sons;

    // Construtor
    public Som() {
        sons = new HashMap<>();
    }

    // Método para adicionar um som ao HashMap
    public void adicionarSom(String nome, String arquivo) {
        if (!sons.containsKey(nome)) {
            GreenfootSound som = new GreenfootSound(arquivo);
            sons.put(nome, som);
        }
    }

    // Método para tocar um som pelo nome
    public void tocarSom(String nome) {
        GreenfootSound som = sons.get(nome);
        if (som != null) { // verifica se o som existe e o reproduz caso exista
            som.play();
        } else {
            System.out.println("Som \"" + nome + "\" não encontrado!");
        }
    }

    // Método para parar um som pelo nome
    public void pararSom(String nome) {
        GreenfootSound som = sons.get(nome);
        if (som != null) {
            som.stop();
        } else {
            System.out.println("Som \"" + nome + "\" não encontrado!");
        }
    }

    // Método para ajustar o volume de um som pelo nome
    public void ajustarVolume(String nome, int volume) {
        GreenfootSound som = sons.get(nome);
        if (som != null) {
            som.setVolume(volume);
        } else {
            System.out.println("Som \"" + nome + "\" não encontrado!");
        }
    }

    // Método para parar todos os sons
    public void pararTodosOsSons() {
        for (GreenfootSound som : sons.values()) {
            som.stop();
        }
    }
}
