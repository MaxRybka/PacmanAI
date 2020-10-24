package com.zetcode;

import java.awt.*;

public class HeuristicPacmanTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Pacman ex = new Pacman();
            ex.HeuristicPacman();
            ex.setVisible(true);
            ex.getBoard().SetPacmanPath(ex.getAgent().startAgent());
        });
    }

}
