package App.Game;

import java.util.ArrayList;
import java.util.List;

public class PlayerState {

    private static PlayerState instance;
    private List<String> selectedPlayers = new ArrayList<>();

    private PlayerState() {}

    public static PlayerState getInstance() {
        if (instance == null) {
            instance = new PlayerState();
        }
        return instance;
    }

    public void saveState() {
        // Aquí puedes guardar otras cosas si deseas
    }

    public void restoreState() {
        // Aquí puedes restaurar otras cosas si deseas
    }

    // Nueva lógica
    public void setSelectedPlayers(List<String> players) {
        this.selectedPlayers = players;
    }

    public List<String> getSelectedPlayers() {
        return selectedPlayers;
    }
}
