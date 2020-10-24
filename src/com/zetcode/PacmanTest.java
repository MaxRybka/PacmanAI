package com.zetcode;

import java.awt.*;

public class PacmanTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Pacman ex = new Pacman();
            ex.Pacman();
            ex.setVisible(true);
            ex.getBoard().SetPacmanPath(ex.getAgent().startAgent());
        });
    }
}
