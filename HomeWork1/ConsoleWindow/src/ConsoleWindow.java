import javax.swing.*;

public class ConsoleWindow {
    public static void main(String[] args) {
        int WIDTH = 800;
        int HEIGHT = 600;
        JFrame frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
