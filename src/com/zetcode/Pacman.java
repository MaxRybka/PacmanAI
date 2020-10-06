package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Pacman extends JFrame {

    private Board board;
    private Agent agent;
    public Pacman() {
        board= new Board();
        initUI();
        agent=new PacmanAgent(new Pair(9,17,null),board);
    }

    private void initUI() {

        add(board);

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(471, 532);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Pacman ex = new Pacman();
            ex.setVisible(true);
            ex.board.SetPacmanPath(ex.agent.startAgent());
        });
    }
}
