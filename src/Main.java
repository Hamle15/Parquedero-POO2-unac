import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Registro().jRegistro);

        ImageIcon image1 = new ImageIcon("eren.png");
        frame.setIconImage(image1.getImage());
        frame.setVisible(true);

    }
}
