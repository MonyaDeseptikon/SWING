import javax.swing.*;


public class GameWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private static final String NAME_OF_WINDOW = "u s m f";

    GameWindow(int WINDOW_POSX, int WINDOW_POSY, int WINDOW_WIDTH, int WINDOW_HEIGHT, String NAME_OF_WINDOW) {
        setTitle(NAME_OF_WINDOW);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }
    GameWindow() {
        setTitle(NAME_OF_WINDOW);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }
}
