import javax.swing.*;
import java.awt.*;

public class MainClass {
    public static void main(String[] args){
        Snake snake = new Snake();
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(500,500));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(snake);
        frame.pack();
        frame.addKeyListener(snake);

    }
}
