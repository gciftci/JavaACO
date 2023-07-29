package main.java.main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.java.inputs.KeyboardInputs;
import main.java.inputs.MouseInputs;
import static main.java.main.Game.GAME_WIDTH;
import static main.java.main.Game.GAME_HEIGHT;
// Constants

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs = new MouseInputs(this);
    private KeyboardInputs keyboardInputs = new KeyboardInputs(this);
    private Game game;

    public GamePanel(Game game) {
        setPanelSize();
        // Add Handlers to Listeners
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        this.game = game;
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("PanelSize: " + GAME_WIDTH + "/" + GAME_HEIGHT);
    }

    public void updateGame() {}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
