package Domain.GameApp;


import Domain.Board.Board;
import Domain.Entity.Characters.Enemies.Enemies;
import Domain.Entity.Characters.Players.Players;

import java.util.ArrayList;

public class Game {

    private int heightScreen;
    private int widthScreen;
    private Board boardGame;

    protected void initSettings() {
        // Inicializar configuración del juego
    }

    protected void initInput() {
        // Inicializar entrada del usuario
    }

    protected void initGameVars() {
        // Inicializar variables del juego
    }

    protected void initPhysics() {
        // Inicializar físicas del juego
    }

    protected void onUpdate() {
        // Actualizar lógica del juego por frame
    }

    protected void initGame() {
        //Método que inicia el juego
    }

    protected void setCharacters(ArrayList<Players> players, ArrayList<Enemies> enemies) {
        // Configurar personajes del juego
    }
}

