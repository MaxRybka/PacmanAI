package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Pacman extends JFrame {

    private Board board;
    private Agent agent;
    public Pacman() {
        board= new Board();
        initUI();
        agent=new PacmanAgent(new Pair(0,0,null),board);
    }

    private void initUI() {

        add(board);

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Pacman ex = new Pacman();
            ex.setVisible(true);
            ex.agent.startAgent();
        });
    }
}
