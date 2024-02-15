package chess;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;


import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);

        Board board = new Board();

        frame.add(board);

        frame.setVisible(true);
    }
}
