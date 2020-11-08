package minimax;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Pacman extends JFrame {

    public Pacman() {

        initUI();
    }

    private void initUI() {

        add(new Board(new Pair(7,11,null),new ArrayList<Pair>(){{
            add(new Pair(4,8,null));
        }}));

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Pacman ex = new Pacman();
            ex.setVisible(true);
        });
    }
}
