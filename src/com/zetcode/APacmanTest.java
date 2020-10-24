package com.zetcode;

import java.awt.*;

public class APacmanTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Pacman ex = new Pacman();
            ex.APacman();
            ex.setVisible(true);
            ex.getBoard().SetPacmanPath(ex.getAgent().startAgent());
        });
    }
}
