package main.java.main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.java.inputs.KeyboardInputs;
import main.java.inputs.MouseInputs;
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
        addMouseMotionListener(mouseInputs);
        this.game = game;
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
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
