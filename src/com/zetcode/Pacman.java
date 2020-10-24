package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Pacman extends JFrame {


    private Agent agent;
    private Pair start_pair = new Pair(9,17,null);
    private Board board= new Board(start_pair, new Pair(18,0,null));

    public Board getBoard() {
        return board;
    }

    public Agent getAgent() {
        return agent;
    }

    public void Pacman() {
        initUI();
        agent = new PacmanAgent(start_pair,board);
    }

    public void APacman(){
        initUI();
        agent = new APacmanAgent(start_pair,board);
    }
    public void HeuristicPacman(){
        initUI();
        agent = new HeuristicPacmanAgent(start_pair,board);
    }

    private void initUI() {
        add(board);
        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(471, 520);
        setLocationRelativeTo(null);
    }


}
