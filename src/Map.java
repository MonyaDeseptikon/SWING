import javax.swing.*;
import java.awt.*;

public class Map extends JPanel{
    public Map() {
        setBackground(Color.BLACK);
           }
    void startNewGame (int mode, int fSzX, int fSzY, int wLen) {
        System.out.printf("Mode: %d; \nxSize: %d, \nySize: %d, \nwinLength: %d", mode, fSzX, fSzY, wLen);
    }
}
